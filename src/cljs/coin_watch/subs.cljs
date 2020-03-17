(ns coin-watch.subs
  (:require
   [re-frame.core :refer [reg-sub subscribe]]))

(reg-sub
 :name
 (fn [db _]
   (:name db)))

(reg-sub
 :sorted-expenses
 (fn [db _]
   (:expenses db)))

(reg-sub
 :expenses
 (fn [query-v _]
   (subscribe [:sorted-expenses]))
 (fn [sorted-expenses query-v _]
   (vals sorted-expenses)))

(reg-sub
 :expense-count
 :<- [:expenses]
 (fn [expenses _]
   (count expenses)))
