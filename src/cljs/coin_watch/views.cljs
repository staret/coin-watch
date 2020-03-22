(ns coin-watch.views
  (:require
   [reagent.core :as reagent]
   [re-frame.core :refer [subscribe dispatch]]
   [coin-watch.subs :as subs]
   [clojure.string :as str]))

(defn movement-input [{:keys [amount on-save on-stop]}]
  (let [val (reagent/atom amount)
        stop #(do (reset! val "")
                  (when on-stop (on-stop)))
        save #(let [v (-> @val str str/trim)]
                (on-save v)
                (stop))]
    (fn [props]
      [:input (merge (dissoc props :on-save :on-stop :amount)
                     {:type "text"
                      :value @val
                      :auto-focus true
                      :on-blur save
                      :on-change #(reset! val (-> % .-target .-value))
                      :on-key-down #(case (.-wich %)
                                      13 (save)
                                      27 (stop)
                                      nil)})])))

(defn movement-item
  []
  (fn [{:keys [id name amount purchase-date]}]
  [:div.view
   [:label str "id " id " - " name]
   [:p amount]
   [:p str "on " purchase-date]
   [:button.edit "edit-me"]]))

(defn movement-list
  []
  (let [movements @(subscribe [:sorted-expenses])]
    [:section#main
     [:div#movement-list
      (for [movement movements]
        ^{:key (:id movement)} [movement-item movement])]]))

(defn coin-status
  []
  [:p "Spent: $xxx.xx"])

(defn movement-entry
  []
  [:div
   [:div
    [movement-input
     {:id "new-movement"
      :placeholder "type the amount"
      :on-save #(when (seq %)
                  (dispatch [:add-movement %]))}]]])

(defn main-panel
  []
  (let [name @(subscribe [:name])]
    [:div
     [:h1 "The " name]
     [:div
      [:p "A very short introduction"]]
     [coin-status]
     (when (seq @(subscribe [:sorted-expenses]))
       [movement-list])
     [movement-entry]]))
