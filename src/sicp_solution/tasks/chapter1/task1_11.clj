(ns sicp-solution.tasks.chapter1.task1-11
  (:use [clojure.test]))

(defn f-recur [n]
  (if (< n 3) 1
    (+ (f-recur (- n 1))
       (f-recur (- n 2)))))

(defn f-iter [n]
  (if (< n 3) 1
    (loop [x (biginteger 1), y 2, counter (- n  3)]
      (if (= 0 counter) y
        (recur y (+ x y) (dec counter))))))

; tests
(deftest f-recur-test
  (is (= 1   (f-iter 1)  (f-recur 1)))
  (is (= 1   (f-iter 2)  (f-recur 2)))
  (is (= 2   (f-iter 3)  (f-recur 3)))
  (is (= 5   (f-iter 5)  (f-recur 5)))
  (is (= 144 (f-iter 12) (f-recur 12))))
