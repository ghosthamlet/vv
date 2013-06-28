(ns vv.util
  (:use hiccup.form hiccup.util clavatar.core)
  (:require [noir.io :as io]
            [markdown.core :as md]))

(defn make-form [& fields]
  (reduce-kv 
    (fn [table i [id name value]]
      (conj table
            [:tr 
             [:td (label id name)] 
             [:td ((if (.startsWith id "pass") password-field text-field) 
                    {:tabindex (inc i)} id value)]]))
    [:table]
    (vec (partition 3 fields))))

(defn format-time
  "formats the time using SimpleDateFormat, the default format is
   \"dd MMM, yyyy\" and a custom one can be passed in as the second argument"
  ([time] (format-time time "dd MMM, yyyy"))
  ([time fmt]
    (.format (new java.text.SimpleDateFormat fmt) time)))

(defn md->html
  "reads a markdown file from public/md and returns an HTML string"
  [filename]
  (->>
    (io/slurp-resource filename)
    (md/md-to-html-string)))
