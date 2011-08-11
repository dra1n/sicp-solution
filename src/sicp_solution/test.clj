(ns sicp-solution.test
  (:use [clojure.contrib.combinatorics :only (cartesian-product)]))

(defn age-combinations
  [age]
  (cartesian-product
   (range 1 age)
   (range 1 age)
   (range 1 age)))

(defn product-equals?
  [lst prod]
  (= (reduce * lst) prod))

(defn print-ages-with-product
  [prod]
  (map #(if (product-equals? % prod) (println %)) (age-combinations prod)))