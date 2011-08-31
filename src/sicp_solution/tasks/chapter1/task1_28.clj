(ns sicp-solution.tasks.chapter1.task1-28
  (:use [sicp-solution.utils :only (square)])
  (:use [clojure.test]))

(defn rem-with-mr-check
  [number m]
  (let [reminder (rem (square number) m)]
    (if (and (not (= number (- m 1)))
             (not (= number 1))
             (= reminder 1))
      0
      reminder)))

(defn expmod
  [base exp m]
  (cond (= exp 0) 1
        (even? exp) (rem-with-mr-check (expmod base (/ exp 2) m) m)
        :else (rem (* base (expmod base (- exp 1) m)) m)))

(defn miller-rabin-test [n]
  (defn try-it [a]
    (= (expmod a (dec n) n) 1))
  (try-it (+ 1 (bigint (rand (- n 2))))))

(defn prime?
  [n times]
  (cond (= times 0) true
        (miller-rabin-test n) (prime? n (- times 1))
        true false))

;tests
(deftest prime-test
  (is (true? (prime? 3 1)))
  (is (true? (prime? 17 10)))
  (is (true? (prime? 199 100)))

  (is (false? (prime? 561 32))) ;Carmichael you just got owned!
  (is (false? (prime? 1105 32))) ;several times
  (is (false? (prime? 1729 32)))
  (is (false? (prime? 2465 32)))

  (is (false? (prime? 10 5)))
  (is (false? (prime? 1127 10)))
  (is (false? (prime? 299095 10)))
  (is (false? (prime? 19999 10))))

(deftest enhanced-expmod-test
  (is (zero? (expmod 561 32)))
  (is (zero? (expmod 1105 32)))
  (is (zero? (expmod 1729 32)))
  (is (zero? (expmod 2465 32))))