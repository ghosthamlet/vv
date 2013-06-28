(ns vv.routes.home
  (:use compojure.core hiccup.core hiccup.form vv.config)
  (:require [vv.views.layout :as layout]
            [vv.models.schema :as schema]
            [vv.locales :as locales]
            [noir.response :as resp]
            [vv.util :as util]))

(defn home-page []
  (layout/render
    "home.html" {:content (util/md->html "/md/docs.md")}))

(defn about-page []
  (layout/render "about.html"))

(defn install [{:keys [host port schema user pass ssl ssl-port error]}]
  (if (= (:initialized @global-config) "prod") 
    (resp/redirect "/")
    (html
      [:body 
       [:h2 "Initial Configuration"]
       (if error [:h2.error error])
       (form-to [:post "/install"]
                (util/make-form "host" (text :host) host
                                "port" (text :port) (or port 5432)
                                "schema" (text :schema) schema
                                "user"   (text :user) user
                                "pass"   (text :password) pass                              
                                "ssl-port" (text :ssl-port) (or ssl-port 443))
                "locale " (drop-down "locale" (map name (keys locales/dict)) "en")
                [:br]
                (label "ssl" (text :ssl?)) (check-box "ssl" false)
                [:br]
                (submit-button (text :initialize)))])))

(defn handle-install [config]  
  (if (= (:initialized @global-config ) "prod")
    (resp/redirect "/")
    (try 
      (save (-> config
              (assoc :initialized true)
              (update-in [:locale] keyword)
              (update-in [:port] #(Integer/parseInt %))
              (update-in [:ssl] #(Boolean/parseBoolean %))
              (update-in [:ssl-port] #(Integer/parseInt %))))
      (schema/reset-db @db)      
      (resp/redirect "/")
      (catch Exception ex
        (install (assoc config :error (.getMessage ex)))))))

(defroutes home-routes
           (GET "/" [] (home-page))
           (GET "/install" {params :params} (install params))
           (POST "/install" {config :params} (handle-install config))
           (GET "/about" [] (about-page)))
