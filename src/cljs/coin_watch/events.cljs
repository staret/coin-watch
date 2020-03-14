(ns coin-watch.events
  (:require
   [re-frame.core :as re-frame]
   [coin-watch.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
