(ns sicp-solution.chapter1.gcd)

(defn gcd [a b]
  (loop [a a, b b]
    (if (= b 0)
      a
      (recur b (rem a b)))))