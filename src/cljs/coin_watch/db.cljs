(ns coin-watch.db
  (:require [cljs.spec.alpha :as s])
  )

(s/def ::id int?)
(s/def ::name string?)
(s/def ::amount float?)
(s/def ::note string?)
(s/def ::creation-date string?)
(s/def ::purchase-date string?)
(s/def ::expense (s/keys :req-un [::id ::name ::amount ::purchase-date ::creation-date]))

(def default-db
  {:name "coin-watch"
   :expenses '({:id 1
                :name "A shot of espresso"
                :amount 30.50
                :note "tasty"
                :purchase-date "2020-01-01 00:00:00"
                :creation-date "2020-01-01 00:00:00"})})
