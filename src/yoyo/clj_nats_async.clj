(ns yoyo.clj-nats-async
  (:require [clj-nats-async.core :as nats]
            [clojure.tools.logging :as log]))

(defn with-nats-client [{:keys [server-urls]} f]
  (log/infof "Starting NATS client against servers: %s" server-urls)
  (let [client (nats/create-nats server-urls)]
    (log/info "Started NATS client.")
    (try
      (f client)
      (finally
        (log/info "Stopping NATS client...")
        (.close client)
        (log/info "Stopped NATS client.")))))
