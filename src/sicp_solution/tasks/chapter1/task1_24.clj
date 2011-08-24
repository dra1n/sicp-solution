(ns sicp-solution.tasks.chapter1.task1-24
  (:use [sicp-solution.tasks.chapter1.task1-22 :only (prime?)])
  (:use [sicp-solution.utils :only (square compare-time)])
  (:use [clojure.test]))

(def test-primes [1009 1013 1019 10007 10009 10037 100003 100019 100043 1000003 1000037 1000039 9999999900000001 1111111111111111111])

(defn expmod
  [base exp m]
  (cond (= exp 0) 1
        (even? exp) (rem (square (expmod base (/ exp 2) m)) m)
        true (rem (* base (expmod base (- exp 1) m)) m)))

(defn fermat-test [n]
  (defn try-it [a]
    (= (expmod a n n) a))
  (try-it (+ 1 (bigint (rand (- n 1))))))

(defn fast-prime?
  [n times]
  (cond (= times 0) true
        (fermat-test n) (fast-prime? n (- times 1))
        true false))

(defn report-prime [elapsed-time]
  (println " *** ")
  (println (str (/ elapsed-time 1.0e6) "msecs")))

(defn start-prime-test [n start-time]
  (if (fast-prime? n 1000)
    (do
      (println n)
      (report-prime (- (System/nanoTime) start-time)))))

(defn timed-prime-test [n]
;  (println n)
  (start-prime-test n (System/nanoTime)))

;evaluations

;(doseq [number test-primes]
;  (timed-prime-test number))


;better benchmarks

; (doseq [number test-primes]
;   (println number)
;   (compare-time (prime? number) (fast-prime? number 100))
;   (println))

; 1009
; Elapsed time for first expression: 0.023397 msecs
; Elapsed time for second expression: 1.950947 msecs

; 1013
; Elapsed time for first expression: 0.029683 msecs
; Elapsed time for second expression: 1.494674 msecs

; 1019
; Elapsed time for first expression: 0.009079 msecs
; Elapsed time for second expression: 1.427277 msecs

; 10007
; Elapsed time for first expression: 0.021092 msecs
; Elapsed time for second expression: 1.61068 msecs

; 10009
; Elapsed time for first expression: 0.020534 msecs
; Elapsed time for second expression: 1.575411 msecs

; 10037
; Elapsed time for first expression: 0.019416 msecs
; Elapsed time for second expression: 10.065661 msecs

; 100003
; Elapsed time for first expression: 0.047213 msecs
; Elapsed time for second expression: 1.824324 msecs

; 100019
; Elapsed time for first expression: 0.051263 msecs
; Elapsed time for second expression: 2.224096 msecs

; 100043
; Elapsed time for first expression: 0.047143 msecs
; Elapsed time for second expression: 1.89214 msecs

; 1000003
; Elapsed time for first expression: 0.126343 msecs
; Elapsed time for second expression: 10.888118 msecs

; 1000037
; Elapsed time for first expression: 0.124318 msecs
; Elapsed time for second expression: 2.143288 msecs

; 1000039
; Elapsed time for first expression: 0.125086 msecs
; Elapsed time for second expression: 2.258386 msecs

; 9999999900000001
; Elapsed time for first expression: 13074.744147 msecs
; Elapsed time for second expression: 6.183747 msecs

; 1111111111111111111
; Elapsed time for first expression: 136259.478598 msecs
; Elapsed time for second expression: 6.679201 msecs

;tests
(deftest fast-prime-test
  (is (true? (fast-prime? 3 1)))
  (is (true? (fast-prime? 17 10)))
  (is (true? (fast-prime? 199 100)))

  (is (false? (fast-prime? 10 5)))
  (is (false? (fast-prime? 1127 10)))
  (is (false? (fast-prime? 299095 10)))
  (is (false? (fast-prime? 19999 10))))