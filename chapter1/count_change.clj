(ns chapter1.count_change
  (:use [clojure.test])
  (:use [clojure.contrib.math :only (expt)]))

(def call-count (atom 0))

(defn first-denomination [kinds-of-coins]
  (cond (= kinds-of-coins 1) 1
        (= kinds-of-coins 2) 5
        (= kinds-of-coins 3) 10
        (= kinds-of-coins 4) 25
        (= kinds-of-coins 5) 50))

(defn cc [amount kinds-of-coins]
  (swap! call-count inc)
  (cond (= amount 0) 1
        (or (< amount 0) (= kinds-of-coins 0)) 0
        true (+ (cc amount
                     (- kinds-of-coins 1))
                (cc (- amount
                       (first-denomination kinds-of-coins))
                     kinds-of-coins))))

(defn count-change [amount]
  (cc amount 5))

; tests
(deftest count-change-test
         (is (= 292 (count-change 100))))
