(ns playground.polymorphism.mp4
  (:require [playground.polymorphism.open-file :refer [open-file]]))

(defprotocol Mp4Decoder
  "A mock mp4 decoder, an example of protocol polymorphism."
  (decode [this file-name]))

(defrecord Mp4Decoder1 []
  Mp4Decoder
  (decode [_ file-name]
    (println "File " file-name " decoded using mp4-decoder-1.")))

(defrecord Mp4Decoder2 []
  Mp4Decoder
  (decode [_ file-name]
    (println "File" file-name "decoded using mp4-decoder-2.")))

(def default-decoder (->Mp4Decoder1))

(defmethod open-file "mp4"
  [filename]
  (decode default-decoder filename))
