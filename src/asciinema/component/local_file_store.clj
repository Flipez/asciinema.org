(ns asciinema.component.local-file-store
  (:require [asciinema.boundary.file-store :as file-store]
            [clojure.java.io :as io]
            [ring.util.http-response :as response]))

(defrecord LocalFileStore [base-path]
  file-store/FileStore

  (put-file [this file path]
    (let [path (str base-path path)]
      (io/make-parents path)
      (io/copy file (io/file path))))

  (put-file [this file path size]
    (file-store/put-file this file path))

  (input-stream [this path]
    (let [path (str base-path path)]
      (io/input-stream path)))

  (move-file [this old-path new-path]
    (let [old-path (str base-path old-path)
          new-path (str base-path new-path)]
      (.renameTo (io/file old-path) (io/file new-path))))

  (delete-file [this path]
    (let [path (str base-path path)]
      (io/delete-file path)))

  (serve-file [this ctx path {:keys [filename]}]
    (let [path (str base-path path)
          response (assoc (:response ctx) :body (io/file path))]
      (if filename
        (update response :headers assoc "content-disposition" (str "attachment; filename=" filename))
        response))))

(defn local-file-store [{:keys [path]}]
  (->LocalFileStore path))
