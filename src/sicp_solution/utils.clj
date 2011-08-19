(ns sicp-solution.utils)

(defn square [n] (* n n))

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