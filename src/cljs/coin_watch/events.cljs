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

(def movement-interceptors [(path :sorted-expenses)])

(defn allocate-next-id
  [expenses]
  (println expenses)
  ((fnil inc 0) (last (keys expenses))))

(reg-event-db
 :add-movement
 [standard-interceptors movement-interceptors]
 (fn [expenses [_ amount]]
   (let [id (allocate-next-id expenses)]
       (assoc expenses id {:id id
                           :name "generic"
                           :amount amount
                           :note "generic note"
                           :purchase-date "2020-01-01 00:00:00"
                           :creation-date (.getTime (js/Date.))}))))
