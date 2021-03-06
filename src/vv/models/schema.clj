(ns vv.models.schema  
  (:require [clojure.java.jdbc :as sql]))

(defn create-assets-table []
  (sql/create-table
    :vv_assets
    [:id "SERIAL" "PRIMARY KEY"]
    [:parent_id :int]
    [:lft :int]
    [:rgt :int]
    [:level :int]
    [:name "varchar(50)"]
    [:title "varchar(100)"]
    [:rules "varchar(5120)"]))

(defn create-associations-table []
  (sql/create-table
    :vv_associations
    [:id "varchar(50)"]
    [:context "varchar(50)"]
    [:key "char(32)"]))

(defn create-banner-clients-table []
  (sql/create-table
    :vv_banner_clients
    [:id "SERIAL" "PRIMARY KEY"]
    [:name "varchar(255)"]
    [:contact "varchar(255)"]
    [:email  "varchar(255)"]
    [:extrainfo  "TEXT"]
    [:state  :smallint]

    [:checked_out  :int]
    [:checked_out_time  :timestamp]
    [:metakey   "TEXT"]
    [:own_prefix :smallint]

    [:metakey_prefix "varchar(50)"]
    [:purchase_type :smallint]
    [:track_clicks :smallint]
    [:track_impressions :smallint]))

(defn create-banner-tracks-table []
  (sql/create-table
    :vv_banner_tracks
    [:track_date :timestamp]
    [:track_type :int]
    [:banner_id :int]
    [:count :int]))

(defn create-banners-table []
  (sql/create-table
    :vv_banners
    [:id "SERIAL" "PRIMARY KEY"]
    [:cid :int]
    [:type :int]
    [:name "varchar(255)"]
    [:alias "varchar(255)"]
    [:imptotal :int]
    [:impmade :int]
    [:clicks :int]
    [:clickurl "varchar(200)"]
    [:state :smallint]
    [:catid :int]
    [:description "text"]
    [:custombannercode "varchar(2048)"]
    [:sticky :smallint]
    [:ordering :int "DEFAULT 0"]
    [:metakey "text"]
    [:params "text"]
    [:own_prefix :boolean]
    [:metakey_prefix "varchar(255)"]
    [:purchase_type :smallint]
    [:track_clicks :smallint]
    [:track_impressions :smallint]
    [:checked_out :int]
    [:checked_out_time :timestamp]
    [:publish_up :timestamp]
    [:publish_down :timestamp]
    [:reset :timestamp]
    [:created :timestamp]
    [:language "char(7)"]
    [:created_by :int]
    [:created_by_alias "varchar(255)"]
    [:modified :timestamp]
    [:modified_by :int]
    [:version :int]))

(defn create-categories-table []
  (sql/create-table
    :vv_categories
    [:id "SERIAL" "PRIMARY KEY"]
    [:asset_id :int]
    [:parent_id :int]
    [:lft :int]
    [:rgt :int]
    [:level :int]
    [:path "varchar(255)"]
    [:extension "varchar(50)"]
    [:title "varchar(255)"]
    [:alias "varchar(255)"]
    [:note "varchar(255)"]
    [:description "text"]
    [:published :boolean]
    [:checked_out :int]
    [:checked_out_time :timestamp]
    [:access :int]
    [:params "text"]
    [:metadesc "varchar(1024)"]
    [:metakey "varchar(1024)"]
    [:metadata "varchar(2048)"]
    [:created_user_id :int]
    [:created_time :timestamp]
    [:modified_user_id :int]
    [:modified_time :timestamp]
    [:hits :int]
    [:language "char(7)"]
    [:version :int]))

(defn create-content-table []
  (sql/create-table
    :vv_content
    [:id "SERIAL" "PRIMARY KEY"]
    [:asset_id :int]
    [:title "varchar(255)"]
    [:alias "varchar(255)"]
    [:introtext "text"]
    [:fulltext "text"]
    [:state :smallint]
    [:catid :int]
    [:created :timestamp]
    [:created_by :int]
    [:created_by_alias "varchar(255)"]
    [:modified :timestamp]
    [:modified_by :int]
    [:checked_out :int]
    [:checked_out_time :timestamp]
    [:publish_up :timestamp]
    [:publish_down :timestamp]
    [:images "text"]
    [:urls "text"]
    [:attribs "varchar(5120)"]
    [:version :int]
    [:ordering :int]
    [:metakey "text"]
    [:metadesc "text"]
    [:access :int]
    [:hits :int]
    [:metadata "text"]
    [:featured :boolean]
    [:language "char(7)"]
    [:xreference "varchar(50)"]))

(defn create-content-frontpage-table []
  (sql/create-table
    :vv_content_frontpage
    [:content_id :int]
    [:ordering :int]))

(defn create-content-rating-table []
  (sql/create-table
    :vv_content_rating
    [:content_id :int]
    [:rating_sum :int]
    [:rating_count :int]
    [:lastip "varchar(50)"]))

(defn create-core-log-searches-table []
  (sql/create-table
    :vv_core_log_searches
    [:search_term "varchar(128)"]
    [:hits :int]))

(defn create-extensions-table []
  (sql/create-table
    :vv_extensions
    [:extension_id "SERIAL" "PRIMARY KEY"]
    [:name "varchar(100)"]
    [:type "varchar(20)"]
    [:element "varchar(100)"]
    [:folder "varchar(100)"]
    [:client_id :smallint]
    [:enabled :smallint]
    [:access :int]
    [:protected :smallint]
    [:manifest_cache "text"]
    [:params "text"]
    [:custom_data "text"]
    [:system_data "text"]
    [:checked_out :int]
    [:checked_out_time :timestamp]
    [:ordering :int]
    [:state :int]))

(defn create-languages-table []
  (sql/create-table
    :vv_languages
    [:lang_id "SERIAL" "PRIMARY KEY"]
    [:lang_code "char(7)"]
    [:title "varchar(50)"]
    [:title_native "varchar(50)"]
    [:sef "varchar(50)"]
    [:image "varchar(50)"]
    [:description "varchar(512)"]
    [:metakey "text"]
    [:metadesc "text"]
    [:sitename "varchar(1024)"]
    [:published :int]
    [:access :int]
    [:ordering :int]))

(defn create-menu-table []
  (sql/create-table
    :vv_menu
    [:id "SERIAL" "PRIMARY KEY"]
    [:menutype "varchar(24)"]
    [:title "varchar(255)"]
    [:alias "varchar(255)"]
    [:note "varchar(255)"]
    [:path "varchar(1024)"]
    [:link "varchar(1024)"]
    [:type "varchar(16)"]
    [:published :smallint]
    [:parent_id :int]
    [:level :int]
    [:component_id :int]
    [:checked_out :int]
    [:checked_out_time :timestamp]
    [:browser_nav :smallint]
    [:access :int]
    [:img "varchar(255)"]
    [:template_style_id :int]
    [:params "text"]
    [:lft :int]
    [:rgt :int]
    [:home :smallint]
    [:language "char(7)"]
    [:client_id :smallint]))

(defn create-menu-types-table []
  (sql/create-table
    :vv_menu_types
    [:id "SERIAL" "PRIMARY KEY"]
    [:menutype "varchar(24)"]
    [:title "varchar(48)"]
    [:description "varchar(255)"]))

(defn create-messages-table []
  (sql/create-table
    :vv_messages
    [:message_id "SERIAL" "PRIMARY KEY"]
    [:user_id_from :int]
    [:user_id_to :int]
    [:folder_id :smallint]
    [:date_time :timestamp]
    [:state :boolean]
    [:priority :boolean]
    [:subject "varchar(255)"]
    [:message "text"]))

(defn create-messages-cfg-table []
  (sql/create-table
    :vv_messages_cfg
    [:user_id :int]
    [:cfg_name "varchar(100)"]
    [:cfg_value "varchar(255)"]))

(defn create-modules-table []
  (sql/create-table
    :vv_modules
    [:id "SERIAL" "PRIMARY KEY"]
    [:title "varchar(100)"]
    [:note "varchar(255)"]
    [:content "text"]
    [:ordering :int]
    [:position "varchar(50)"]
    [:checked_out :int]
    [:checked_out_time :timestamp]
    [:publish_up :timestamp]
    [:publish_down :timestamp]
    [:published :smallint]
    [:module "varchar(50)"]
    [:access :int]
    [:showtitle :smallint]
    [:params "text"]
    [:client_id :smallint]
    [:language "char(7)"]))

(defn create-modules-menu-table []
  (sql/create-table
    :vv_modules_menu
    [:module_id :int]
    [:menu_id :int]))

(defn create-overrider-table []
  (sql/create-table
    :vv_overrider
    [:id "SERIAL" "PRIMARY KEY"]
    [:constant "varchar(255)"]
    [:string "text"]
    [:file "varchar(255)"]))

(defn create-schemas-table []
  (sql/create-table
    :vv_schemas
    [:extension_id :int]
    [:version_id "varchar(20)"]))

(defn create-session-table []
  (sql/create-table
    :vv_session
    [:session_id "varchar(200)"]
    [:client_id :smallint]
    [:guest :smallint "DEFAULT 1"]
    [:time "varchar(14)"]
    [:data "text"]
    [:user_id :int]
    [:username "varchar(150)"]))

(defn create-template-styles-table []
  (sql/create-table
    :vv_template_styles
    [:id "SERIAL" "PRIMARY KEY"]
    [:template "varchar(50)"]
    [:client_id :smallint]
    [:home "char(7)"]
    [:title "varchar(255)"]
    [:params "text"]))

(defn create-update-sites-table []
  (sql/create-table
    :vv_update_sites
    [:update_site_id "SERIAL" "PRIMARY KEY"]
    [:name "varchar(100)"]
    [:type "varchar(20)"]
    [:location "text"]
    [:enabled :boolean]
    [:last_check_timestamp :int]))

(defn create-update-sites-extensions-table []
  (sql/create-table
    :vv_update_sites_extensions
    [:update_site_id :int]
    [:extension_id :int]))

(defn create-updates-table []
  (sql/create-table
    :vv_updates
    [:update_id "SERIAL" "PRIMARY KEY"]
    [:update_site_id :int]
    [:extension_id :int]
    [:name "varchar(100)"]
    [:description "text"]
    [:element "varchar(100)"]
    [:type "varchar(20)"]
    [:folder "varchar(20)"]
    [:client_id :smallint]
    [:version "varchar(10)"]
    [:data "text"]
    [:details_url "text"]
    [:info_url "text"]))

(defn create-user-notes-table []
  (sql/create-table
    :vv_user_notes
    [:id "SERIAL" "PRIMARY KEY"]
    [:user_id :int]
    [:catid :int]
    [:subject "varchar(100)"]
    [:body "text"]
    [:state :smallint]
    [:checked_out :int]
    [:checked_out_time :timestamp]
    [:created_user_id :int]
    [:created_time :timestamp]
    [:modified_user_id :int]
    [:modified_time :timestamp]
    [:review_time :timestamp]
    [:publish_up :timestamp]
    [:publish_down :timestamp]))

(defn create-user-profiles-table []
  (sql/create-table
    :vv_user_profiles
    [:user_id :int]
    [:profile_key "varchar(100)"]
    [:profile_value "varchar(255)"]
    [:ordering :int]))

(defn create-user-usergroup-map-table []
  (sql/create-table
    :vv_user_usergroup_map
    [:user_id :int]
    [:group_id :int]))

(defn create-usergroups-table []
  (sql/create-table
    :vv_usergroups
    [:id "SERIAL" "PRIMARY KEY"]
    [:parent_id :int]
    [:lft :int]
    [:rgt :int]
    [:title "varchar(100)"]))

(defn create-users-table []
  (sql/create-table
    :vv_users
    [:id "SERIAL" "PRIMARY KEY"]
    [:name "varchar(255)"]
    [:username "varchar(150)"]
    [:email "varchar(100)"]
    [:password "varchar(100)"]
    [:block :smallint]
    [:send_email :smallint]
    [:register_date :timestamp]
    [:last_visit_date :timestamp]
    [:activation "varchar(100)"]
    [:params "text"]
    [:last_reset_time :timestamp]
    [:reset_count :int]))

(defn create-viewlevels-table []
  (sql/create-table
    :vv_viewlevels
    [:id "SERIAL" "PRIMARY KEY"]
    [:title "varchar(100)"]
    [:ordering :int]
    [:rules "varchar(5120)"]))

(defn create-finder-filters-table []
  (sql/create-table
    :vv_finder_filters
    [:filter_id "SERIAL" "PRIMARY KEY"]
    [:title "varchar(255)"]
    [:alias "varchar(255)"]
    [:state :boolean]
    [:created :timestamp]
    [:created_by :int]
    [:created_by_alias "varchar(255)"]
    [:modified :timestamp]
    [:modified_by :int]
    [:checked_out :int]
    [:checked_out_time :timestamp]
    [:map_count :int]
    [:data "text"]
    [:params "text"]))

(defn drop-table
  [table]
  (try
    (sql/drop-table table)
    (catch Exception _)))

(def all-table [:vv_assets 
                :vv_associations 
                :vv_banner_clients 
                :vv_banner_tracks 
                :vv_banners 
                :vv_categories 
                :vv_content 
                :vv_content_frontpage 
                :vv_content_rating 
                :vv_core_log_searches 
                :vv_extensions 
                :vv_languages 
                :vv_menu 
                :vv_menu_types 
                :vv_messages 
                :vv_messages_cfg 
                :vv_modules 
                :vv_modules_menu 
                :vv_overrider 
                :vv_schemas 
                :vv_session 
                :vv_template_styles 
                :vv_update_sites 
                :vv_update_sites_extensions 
                :vv_updates 
                :vv_user_notes 
                :vv_user_profiles 
                :vv_user_usergroup_map 
                :vv_users 
                :vv_usergroups 
                :vv_viewlevels 
                :vv_finder_filters])

(defn reset-db [db]  
  (sql/with-connection 
    db
    (dorun (map #(drop-table %) all-table))
    (sql/transaction
      (try
        (dorun (map #((eval
                        (read-string
                          (str "vv.models.schema/" "create-" 
                               (clojure.string/replace (subs (str %) 4) "_" "-") 
                               "-table"))))
                    all-table))
        (catch Exception ex
          (spit "log/error.log" ex))))
    nil))

