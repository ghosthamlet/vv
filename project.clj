(defproject vv "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [lib-noir "0.6.2"]
                 [compojure "1.1.5"]
                 [ring-server "0.2.8"]
                 [clabango "0.5"]
                 [clavatar "0.2.1"]
                 [com.taoensso/timbre "2.1.2"]
                 [com.postspectacular/rotor "0.1.0"]
                 [com.taoensso/tower "1.7.1"]
                 [markdown-clj "0.9.26"]
                 [org.clojure/java.jdbc "0.2.3"]   
                 [postgresql/postgresql "9.1-901.jdbc4"]
                 [korma "0.3.0-RC5"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler vv.handler/war-handler
         :init    vv.handler/init
         :destroy vv.handler/destroy}
  :profiles
  {:production {:ring {:open-browser? false
                       :stacktraces?  false
                       :auto-reload?  false}}
   :dev {:dependencies [[ring-mock "0.1.5"]
                        [ring/ring-devel "1.1.8"]
                        [vimclojure/server "2.3.0"]]}}
  :min-lein-version "2.0.0")
