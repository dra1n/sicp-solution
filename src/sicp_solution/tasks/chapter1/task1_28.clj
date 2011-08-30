(ns sicp-solution.tasks.chapter1.task1-28
  (:use [sicp-solution.utils :only (square)])
  (:use [clojure.test]))

(defn expmod
  [base exp m]
  (cond (= exp 0) 1
        (even? exp) (rem (square (expmod base (/ exp 2) m)) m)
        true (rem (* base (expmod base (- exp 1) m)) m)))

(defn miller-rabin-number?
  [a n]
  (and (not (or (= a 1)
                (= a (dec n))))
       (= (rem (square a) n)
          1)))

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
(deftest fast-prime-test
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