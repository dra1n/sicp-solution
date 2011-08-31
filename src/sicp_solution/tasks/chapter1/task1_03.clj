(ns sicp-solution.tasks.chapter1.task1-03
  (:use [sicp-solution.utils :only (square)])
  (:use [clojure.test]))

(defn max-of-two
  [a b]
  (if (< a b) b a))

(defn sum-of-squares
  [a b]
  (+ (square a) (square b)))

(defn sum-of-max
  [a b c]
  (if (= (max-of-two a b) a)
    (sum-of-squares a (max-of-two b c))
    (sum-of-squares b (max-of-two a c))))

(deftest sum-of-max-test
  (is (= 34 (sum-of-max 5 2 3)))
  (is (= 13 (sum-of-max 1 2 3)))
  (is (= 8 (sum-of-max 2 2 2)))
  (is (= 18 (sum-of-max 3 3 1))))
