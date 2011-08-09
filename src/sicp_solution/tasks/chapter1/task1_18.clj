(ns sicp-solution.tasks.chapter1.task1-18
  (:use [clojure.test]))

(defn doub [num] (+ num num))

(defn halve [num] (/ num 2))

(defn mult-iter
  [num1 num2]
  (loop [a num1, b num2, res 0]
    (cond (= b 0) res
          (even? b) (recur (doub a) (halve b) res)
          true (recur a (- b 1) (+ res a)))))

;tests
(deftest mult-test
  (is (= (mult-iter 2 2) (* 2 2)))
  (is (= (mult-iter 5 8) (* 5 8)))
  
  (is (= (mult-iter -10 5) (* -10 5))))