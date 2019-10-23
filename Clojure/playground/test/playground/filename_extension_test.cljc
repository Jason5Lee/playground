(ns playground.filename-extension-test
  (:require [clojure.test :refer :all]
            [playground.polymorphism.open-file :refer [filename->extension]]))

(testing "Testing empty"
  (is (nil? (filename->extension "")))
  (is (nil? (filename->extension "abc"))))

(testing "Testing one dot"
  (is (= (filename->extension ".clj") "clj")))