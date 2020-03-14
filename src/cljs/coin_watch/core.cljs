(ns coin-watch.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [coin-watch.events :as events]
   [coin-watch.views :as views]
   [coin-watch.config :as config]
   ))

(enable-console-print!)

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
