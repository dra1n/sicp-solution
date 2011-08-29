(ns sicp-solution.tasks.chapter1.task1-27
  (:use [sicp-solution.utils :only (square)])
  (:use [clojure.test]))

(def carmichael-numbers [561 1105 1729 2465 2821 6601])

(defn expmod
  [base exp m]
  (cond (= exp 0) 1
        (even? exp) (rem (square (expmod base (/ exp 2) m)) m)
        true (rem (* base (expmod base (- exp 1) m)) m)))

(defn pass-fermat-test?
  [n]
  (every? #(= % (expmod % n n))
          (range n)))

(defn try-carmichael-numbers
  []
  (every? pass-fermat-test? carmichael-numbers))

;test
(deftest try-them-all
  (is true? try-carmichael-numbers)
  (is false? (pass-fermat-test? 10)))