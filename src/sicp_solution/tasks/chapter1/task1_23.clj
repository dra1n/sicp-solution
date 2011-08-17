(ns sicp-solution.tasks.chapter1.task1-23
  (:require [sicp-solution.tasks.chapter1.task1-22 :as old])
  (:use [sicp-solution.tasks.chapter1.task1-21 :only (smallest-divisor divides?)])
  (:use [clojure.test]))

(def test-primes [1009 1013 1019 10007 10009 10037 100003 100019 100043 1000003 1000037 1000039])

(defmacro compare-time
  [expr1 expr2]
  `(let [start1# (. System (nanoTime))
         ret1# ~expr1
         end1# (. System(nanoTime))
         start2# (. System (nanoTime))
         ret2# ~expr2
         end2# (. System(nanoTime))]
     (println (str "Elapsed time for first expression: " (/ (double (- end1# start1#)) 1000000.0) " msecs"))
     (println (str "Elapsed time for second expression: " (/ (double (- end2# start2#)) 1000000.0) " msecs"))
     ret1#))

(defn nxt [n] (if (< n 3) 3 (+ n 2)))

(defn square [n] (* n n))

(defn find-divisor
  [n test-divisor]
  (loop [n n, test-divisor (inc test-divisor)]
    (cond (> (square test-divisor) n) n
          (divides? test-divisor n) test-divisor
          true (recur n (nxt test-divisor)))))

(defn prime? [n]
  (= n (smallest-divisor n find-divisor)))

; lets roll

; (doseq [n test-primes]
;   (println n)
;   (compare-time (prime? n) (old/prime? n))
;   (println))


; strange shit
; "enhanced" find-divisor works slower

; 1009
; Elapsed time for first expression: 0.209035 msecs
; Elapsed time for second expression: 0.014247 msecs

; 1013
; Elapsed time for first expression: 0.015924 msecs
; Elapsed time for second expression: 0.003981 msecs

; 1019
; Elapsed time for first expression: 0.015574 msecs
; Elapsed time for second expression: 0.003841 msecs

; 10007
; Elapsed time for first expression: 0.041416 msecs
; Elapsed time for second expression: 0.010057 msecs

; 10009
; Elapsed time for first expression: 0.040578 msecs
; Elapsed time for second expression: 0.009149 msecs

; 10037
; Elapsed time for first expression: 0.040927 msecs
; Elapsed time for second expression: 0.009149 msecs

; 100003
; Elapsed time for first expression: 0.116984 msecs
; Elapsed time for second expression: 0.027238 msecs

; 100019
; Elapsed time for first expression: 0.115378 msecs
; Elapsed time for second expression: 0.026819 msecs

; 100043
; Elapsed time for first expression: 0.115796 msecs
; Elapsed time for second expression: 0.026959 msecs

; 1000003
; Elapsed time for first expression: 0.354165 msecs
; Elapsed time for second expression: 0.081505 msecs

; 1000037
; Elapsed time for first expression: 0.357587 msecs
; Elapsed time for second expression: 0.085905 msecs

; 1000039
; Elapsed time for first expression: 0.355352 msecs
; Elapsed time for second expression: 0.081155 msecs

;tests
(deftest nxt-test
  (is (= (nxt 1) 3))
  (is (= (nxt 2) 3))
  (is (= (nxt 4) 6)))

(deftest prime-test
  (is (true? (prime? 3)))
  (is (true? (prime? 17)))
  (is (true? (prime? 199)))

  (is (false? (prime? 10)))
  (is (false? (prime? 19999))))