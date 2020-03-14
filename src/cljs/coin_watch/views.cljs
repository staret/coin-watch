(ns coin-watch.views
  (:require
   [re-frame.core :as re-frame]
   [coin-watch.subs :as subs]
   ))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 "Hello from " @name]
     ]))
