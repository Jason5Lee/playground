(ns playground.polymorphism.open-file
  (:require [clojure.string :as s]))

(defn filename->extension
  "Returns the filename extension of given filename."
  [filename]
  (if-let [dot-index (s/last-index-of filename ".")]
    (subs filename (inc dot-index))))

(defmulti open-file
          "The way of opening a file depends on its filename extension,
          an example of polymorphism using multi-method."
          filename->extension)

(defmethod open-file nil
  [filename]
  (throw (ex-info "Filename has no extension."
                  {:cause    "Filename need to have extension to be open."
                   :filename filename})))

