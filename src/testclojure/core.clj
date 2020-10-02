(ns testclojure.core
  (:gen-class)
  (:require [clojure.string :as str])
  (:import (javax.swing JFrame JTextField)))

;run line cmd + shift + p
;send file to REPL cmd + shift + l
;variables always immutable
;defn create a function always between ()
(type false)

(def myLong 15)
(nil? myLong)
(pos? 15)
(neg? 12)
(even? 13)
(odd? 13)
(number? 123)
(integer? 123)
(float? 123)
(zero? 123)

;format with print
(def myString "Hello")
(def myDouble 1.23456)
(format "This is  a string %s" myString)
(format  "5 spaces and %5d" myLong)
(format "Leading zeros %04d" myLong)
(format "%-4d left justified" myLong)
(format "3 decimals %.3f" myDouble)

(def str1 "This is my 2nd String")
(format "My String is %s" str1)
(str/blank? str1)
(str/includes? str1 "my")
(str/index-of str1 "my")
(str/split  str1 #" ")
(str/split str1 #"\d")
(str/join " " ["The" "Big" "Cheese"])
(str/replace "I am 42" #"42" "43")
(str/upper-case str1)
(str/lower-case str1)

;List
(println (list "Dog", 1 4.3 true))
(println (first (list "Dog" 1 4.3 true)))
(println (rest (list "Dog" 1 4.3 true)))
(println (nth (list "Dog" 1 4.3 true) 0))                  ;return the value by the index
(println (list* "Dog" 1 [2 3] ) )                          ;adds value to a list
(println (cons 3 (list "Dog" 1 ) ))                         ;adds value to the begin of the list

;Set
(println (set '(1 2 2 )))
(println (conj (set '(1 2)) 3))                             ;adds a new value to the set
(println (disj (set '(1 2)) 2))                             ;removes value to the set

(println (get (set '(2 1 3)) 4))                            ;try to get the value inside the set

(println (contains? (set '(1 2)) 2))

;Vector
(println (vector 1 2 3 4 5 ))
(println (get (vector 1 2 3 4 5 ) 1))
(println (pop (vector 1 2 3 4 5 )))                         ;removes last item
(println (subvec (vector 1 2 3 4 5 ) 1 4))                  ;creates a subvector removing position 0 and 5

;Map
(println (hash-map "Name" "Ricardo" "Age" 38))
(println (sorted-map 3 42 2 "Bananas" 1 "Ricardo"))
(println (get (sorted-map 3 42 2 "Bananas" 1 "Ricardo") 3))
(println (find (sorted-map 3 42 2 "Bananas" 1 "Ricardo") 3 ))
(println (contains? (sorted-map 3 42 2 "Bananas" 1 "Ricardo") 3 ))
(println (keys (sorted-map 3 42 2 "Bananas" 1 "Ricardo")))
(println (vals (sorted-map 3 42 2 "Bananas" 1 "Ricardo")))
(println (merge-with + (sorted-map 3 "ABC" 2 "Bananas") (hash-map 1 "Ricardo")))

;filter lists
(take 2 [1 2 3 ])
(drop 1 [1 2 3 ])
(take-while neg? [ -2 -1 0 1 2])
(drop-while neg? [ -2 -1 0 1 2])
(filter #(> % 2) [ 1 2 3 4])
;Atoms are a data type in Clojure that provide a way to manage shared,
; synchronous, independent state. An atom is just like any reference type
; in any other programming language.
; The primary use of an atom is to hold Clojure’s immutable datastructures.
; The value held by an atom is changed with the swap! method
(defn atom-ex
  [x]
  (def atomEx (atom x))
  (add-watch atomEx :watcher
             (fn [key atom old-state new-state]
               (println "atomEx changed from " old-state " to " new-state)))
  (println "1st x" @atomEx)
  (reset! atomEx 10)
  (println "2nd x" @atomEx)
  (swap! atomEx inc)
  (println "Increment x" @atomEx)
  )
(atom-ex 5)

;agents provide independent, asynchronous change of individual locations.
; Agents are bound to a single storage location for their lifetime,
; and only allow mutation of that location (to a new state) to occur as a result
; of an action. Actions are functions (with, optionally, additional arguments)
; that are asynchronously applied to an Agent’s state and whose return value becomes
; the Agent’s new state


(comment
(defn agent-ex
  []
  (def tickets-sold (agent 0))
  (println)
  (send tickets-sold + 15)
  (println "Tickets " @tickets-sold)

  (send tickets-sold + 10)
  (await-for 100 tickets-sold)
  (println "Tickets " @tickets-sold)
  (shutdown-agents)

  )
(agent-ex)

)


;Math
(+ 1 2)
(- 1 2)
(* 1 2)
(/ 1 2)
(mod 1 2)
(inc 5)
(dec 5)
(Math/abs -10)
(Math/cbrt 8)                                               ;Cube Root
(Math/sqrt 4)                                               ;Square Root
(Math/ceil 4.5)                                             ;round up
(Math/floor 4.5)                                            ;round down
(Math/exp 1)                                                ;e to the power of 1
(Math/hypot 2 2)                                            ;sqrt(x^2 y^2
(Math/log 2.71828)                                          ;Natural logarithm
(Math/log10 100)                                            ;base 10 log
(Math/max 1 5)
(Math/min 1 5)
(Math/pow 2 2)                                              ;pow
(println (rand-int 20))
(reduce + [1 2 3])
(. Math PI)

(def five 5)
(def sqr (fn [x] (* x x)))
(sqr five)


;functions
(defn say-hello
  "Receives a name with one parameter and responds"
  [name]
  (println "Hello again " name)
  )


(defn get-sum
  [x y]
  (+ x y ))

(get-sum 2 3 )

(defn get-sum-more
  ( [x y z]
   (println  (+ x y z )))

  ( [x y]
   (println  (+ x y )))
  )

(defn hello-you
  [name]
  (str "Hello " name))

(defn hello-all
  [& names]
  (map hello-you names))
(say-hello "Ricardo")
(get-sum-more 1 2)
(get-sum-more 1 2 3)
(hello-all "Ricardo" "Barbara" "Henrique")


;decision making
(= 4 5)
(not= 4 5)
(and true false)
(or true false)
(not true)

(defn can-vote
  [age]
  (if (>= age 18)
    (println "You can vote")
    (println "You cannot vote"))
  )

(defn can-do-more
  [age]
  (if (>= age 18)
    (do
      (println "You can vote")
      (println "You can drive")
    )
    (println "You cannot vote")
    ))

(defn when-example
  [tof]
  (when tof (println "1st thing" ) (println "2nd thing")))

(defn what-grade
  [n]
  (cond
    (< n 5 ) (println "Preschool")
    (= n 5) (println "Kindergarden")
    (and (> n 5) (<= n 18) ) (format "Go to grade %d" (- n 5))
    :else "Go to college"
    )
  )
(can-vote 17)
(can-do-more 19)
(when-example true)
(what-grade 4)
(what-grade 5)
(what-grade 13)
(what-grade 19)

;loops

(defn one-to-x
  [x]
  (def i (atom 1))
  (while (<= @i x)
    (do
      (println @i)
      (swap! i inc)
      )
    )
  )

(defn double-to-x
  [x]
  (dotimes [i x]
    (println * i 2)
    )
 )

(defn triple-to-x
  [x y]
  (loop [i x]
    (when (< i y)
      (println (* i 3))
      (recur (+ i 1))
      )
    )
  )

(one-to-x 10)
(double-to-x 4)
(triple-to-x 1 5)

;iterate over a sequence
(defn print-list
  [& nums]
  (doseq [x nums]
    (println x))
  )
(print-list 1 2 3 4 5 6 7 20)


;file library
(use 'clojure.java.io)

(defn write-to-file
  [file text]
  (with-open [wrtr (writer file)]
    (.write wrtr text))
)

(defn read-from-file
  [file]
  (try
    (println ( slurp file))
  (catch Exception e (println "Error : " (.getMessage e)))))

(defn append-to-file
  [file text]
  (with-open [wrtr (writer file :append true)]
    (.write wrtr text)))

(defn read-line-from-file
  [file]
  (with-open [rdr (reader file)]
    (doseq [line (line-seq rdr)]
      (println line))))

(write-to-file "test.txt" "This is my text\n")
(read-from-file "test.txt")
(append-to-file "test.txt" "This is another text\n")
(read-line-from-file "test.txt")

;destructuring
(defn destruct
  []
  (def vectVals [1 2 3 4])
  (let [[one two & the-rest] vectVals]
    (println one two the-rest)
    )
  )
(destruct)
(new java.util.Date)

;struct map
(defn struct-map-ex
 []
  (defstruct Customer :Name :Phone)
  (def cust1 (struct Customer "Ricardo" 1234567) )
  (def cust2 (struct-map Customer :Name "Henrique" :Phone 12234324))

  (println cust1)
  (println (:Name cust2))
  )

(struct-map-ex)

;advanced functions
;anonymous functions
(map (fn [x] (* x x )) (range 1 10))
;compact anonymous function
(map #(* % 3) (range 1 10))

(#(* %1 %2) 2 3)

;clojures
(defn custom-multiplier
  [mult-by]
  #(* % mult-by))

(def mult-by-three
  (custom-multiplier 3)
  )

(mult-by-three 3)

;macros - generate code inline (code generate tool)
;`if -> do not evaluate the if, just return it
(defmacro discount
  ([cond discount1 discount2]
    (list `if cond discount1 discount2)
   ))

(discount (> 25 65)  (println "10% off")
          (println "Full price"))

(defmacro regular-math
  [calc]
  (list (second calc) (first calc) (nth calc 2))
  )

(regular-math (2 + 5))

(defmacro do-more
  [cond & body]
  (list `if cond (cons 'do body))
  )
(do-more (< 1 2) (println "Hello") (println "Another command"))


(defmacro do-more-two
  [cond & body]
  `(if ~cond (do ~@body))
  )

(do-more-two (< 1 2) (println "Hello") (println "Another command"))


(import '(javax.swing JFrame JLabel JTextField, JButton, SwingUtilities)
        '(java.awt.event ActionListener) '(java.awt GridLayout))

(defn celsius []
  (let [frame (JFrame. "Celsius Converter")
        temp-text (JTextField.)
        celsius-label (JLabel. "Celsius")
        convert-button (JButton. "Convert")
        fahrenheit-label (JLabel. "Fahrenheit")]
    (.addActionListener convert-button
                        (proxy [ActionListener] []
                          (actionPerformed [evt]
                            (let [c (. Double parseDouble (.getText temp-text))]
                              (.setText fahrenheit-label
                                        (str (+ 32 (* 1.8 c)) " Fahrenheit"))))))
    (doto frame
      (.setLayout (GridLayout. 2 2 3 3 ))
      (.add temp-text) (.add celsius-label)
      (.add convert-button) (.add fahrenheit-label)
      (.setSize 300 70) (.setVisible true))))

(celsius)

