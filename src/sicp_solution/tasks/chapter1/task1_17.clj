(ns sicp-solution.tasks.chapter1.task1-17
  (:use [clojure.test]))

(defn doub [num] (+ num num))

(defn halve [num] (/ num 2))

(defn mult
  [a b]
  (cond (= b 0) 0
        (even? b) (mult (doub a) (halve b))
        true (+ a (mult a (- b 1)))))

;tests
(deftest mult-test
  (is (= (mult 2 2) (* 2 2)))
  (is (= (mult 5 8) (* 5 8)))
  
  (is (= (mult -10 5) (* -10 5))))