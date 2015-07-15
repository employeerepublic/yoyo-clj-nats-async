(ns yoyo.clj-nats-async
  (:require [clj-nats-async.core :as nats]
            [clojure.tools.logging :as log]))

(defn with-nats-client [{:keys [nats-opts]} f]
  (log/infof "Starting NATS client with opts: %s" (prn-str nats-opts))
  (let [{:keys [server-urls]} nats-opts
        client (nats/create-nats server-urls)]
    (log/info "Started NATS client.")
    (try
      (f client)
      (finally
        (log/info "Stopping NATS client...")
        (.close client)
        (log/info "Stopped NATS client.")))))
