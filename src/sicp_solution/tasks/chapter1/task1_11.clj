(ns sicp-solution.tasks.chapter1.task1-11
  (:use [clojure.test]))

(defn f-recur [n]
  (if (< n 3) n
    (+ (f-recur (- n 1))
       (f-recur (- n 2))
       (f-recur (- n 3)))))

(defn f-iter [n]
  (if (< n 3)
    n
    (loop [n1 1, n2 2, n3 3, counter (- n 3)]
      (if (= 0 counter)
        n3
        (recur n2 n3 (+ n1 n2 n3) (dec counter))))))

; tests
(deftest f-recur-test
  (is (= 1 (f-iter 1) (f-recur 1)))
  (is (= 2 (f-iter 2) (f-recur 2)))
  (is (= 3 (f-iter 3) (f-recur 3)))
  (is (= 11 (f-iter 5) (f-recur 5)))
  (is (= 68 (f-iter 8) (f-recur 8))))
