(ns tasks.chapter1.task1_3
  (:use [clojure.test]))

(defn sum-of-max
  ([x1 x2 x3]
    (let [coll [x1 x2 x3]]
      (sum-of-max (rest (sort coll)))))
  ([coll]
    (reduce #(+ %1 (* %2 %2)) 0 coll)))

(deftest sum-of-max-test
  (is (= 34 (sum-of-max 5 2 3)))
  (is (= 13 (sum-of-max 1 2 3))))
