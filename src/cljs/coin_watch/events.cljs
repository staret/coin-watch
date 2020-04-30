(ns coin-watch.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx inject-cofx path after debug]]
   [coin-watch.db :as db]
   ))

(reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(def standard-interceptors [debug])

(defn allocate-next-id
  [expenses]
  ((fnil inc 0) (get (first expenses) :id)))

(reg-event-db
 :add-movement
 [standard-interceptors]
 (fn [db [_ amount]]
   (update db :expenses conj {:id (allocate-next-id (get db :expenses))
                              :name "generic"
                              :amount amount
                              :note "generic note"
                              :purchase-date "2020-01-01 00:00:00"
                              :creation-date (.getTime (js/Date.))})))

(reg-event-db
 :add-name
 [standard-interceptors]
 (fn [db [_ name]]
   (update db :names conj name)))
