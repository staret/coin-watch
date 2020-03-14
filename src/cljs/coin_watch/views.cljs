(ns coin-watch.views
  (:require
   [re-frame.core :as re-frame]
   [coin-watch.subs :as subs]))

(defn expense-item
  []
  (fn [{:keys [name amount purchase-date]}]
  [:div.view
   [:label name]
   [:p amount]
   [:p str "on " purchase-date]
   [:button.edit]]))

(defn expense-list
  []
  (let [expenses (re-frame/subscribe [::subs/expenses])]
    [:section#main
     [:div#expense-list
      (for [expense @expenses]
            ^{:key (:id expense)} [expense-item expense])]]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 "The " @name]
     [:div
      [:p "A very short introduction"]]
     [:div (expense-list)]]))
