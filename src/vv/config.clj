(ns vv.config
  (:use clojure.java.io vv.models.schema vv.locales)  
  (:import java.io.File
           java.sql.DriverManager
           org.postgresql.ds.PGPoolingDataSource))

(def global-config (atom nil))
(def db (atom nil))

(defn text [tag]
  (get (get dict (get @global-config :locale :en)) tag "<no translation available>"))

(defn load-config-file []
  (let [url (resource "global.properties")]    
    (if (or (nil? url) (.. url getPath (endsWith "jar!/global.properties")))
      (doto (new File "global.properties") (.createNewFile))
      url)))

(defn reset [config]  
  (reset! db 
          {:datasource 
           (doto (new PGPoolingDataSource)
             (.setServerName   (:host config) )
             (.setDatabaseName (:schema config))
             (.setPortNumber   (:port config))
             (.setUser         (:user config))                                  
             (.setPassword     (:pass config)))})
  (reset! global-config (select-keys config [:ssl :ssl-port :initialized :locale])))

(defn init []  
  (with-open
    [r (java.io.PushbackReader. (reader (load-config-file)))]    
    (if-let [config (read r nil nil)]      
      (reset config)))  
  (println "intialized"))

(defn save [config]   
  (with-open [con (DriverManager/getConnection 
                    (str "jdbc:postgresql://" (:host config) "/" (:schema config)) (:user config) (:pass config))])
  (with-open [w (clojure.java.io/writer (load-config-file))]
    (.write w (str config))
    (reset config)))


