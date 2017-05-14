(ns fxc.marshalling-test
  (:use midje.sweet)
  (:require [clojure.pprint :as pp]
            [fxc.core :refer :all]
            [fxc.marshalling :as ms]
            [fxc.intcomp     :as ic ]))

(pp/pprint {"------------------------------------------" "MARSHALLING_TESTS"})

;; random share
(def testshare "LLDH5L89PP5VBKQQWNE9DUQMDLKQDLUM5RG8ZKRB8R9296RWHXE8ZR32BKWV34RGRF9NW54LZXHR7G45M4RFZZXE473VFLXXZ7D52SD5KX379RHWL5DV9GPSRVE3K9PLHMXEWZ6W6SLDVX8DQEU48VLDEEB29L6DGNKTNE8DPR39SM")
(def testintseq [76 76 68 72 53 76 56 57 80 80 53 86 66 75 81 81 87 78 69 57 68 85 81 77 68 76 75 81 68 76 85 77 53 82 71 56 90 75 82 66 56 82 57 50 57 54 82 87 72 88 69 56 90 82 51 50 66 75 87 86 51 52 82 71 82 70 57 78 87 53 52 76 90 88 72 82 55 71 52 53 77 52 82 70 90 90 88 69 52 55 51 86 70 76 88 88 90 55 68 53 50 83 68 53 75 88 51 55 57 82 72 87 76 53 68 86 57 71 80 83 82 86 69 51 75 57 80 76 72 77 88 69 87 90 54 87 54 83 76 68 86 88 56 68 81 69 85 52 56 86 76 68 69 69 66 50 57 76 54 68 71 78 75 84 78 69 56 68 80 82 51 57 83 77]) ;; feed to compress
(def testintcomp [174 117901063 1494296140 1349706339 1546308968 1733796678 1185695537 853951643 -1688837046 -1491998411 948259421 -1281995159 1816702793 -829053167 -706362780 -1890999494 2043568978 1519964589 997872172 -1706202414 1658169556 -858016629 -1318951830 649141210 1265308315 -1818825492 449621794 1111202513 1902858919 -1723741002 7 2025203400 -1230054699 -983002327 1657899234 850626197 1352844681 -1998953578 -724840761 -994523698 -1179397424 52691])
(def testuintcomp [174 2265384710N 3641779787N 3497189986N 3693792615N 3881280325N 3333179184N 3001435290N 458646601N 655485236N 3095743068N 865488488N 3964186440N 1318430480N 1441120867N 256484153N 4191052625N 3667448236N 3145355819N 441281233N 3805653203N 1289467018N 828531817N 2796624857N 3412791962N 328658155N 2597105441N 3258686160N 4050342566N 423742645N 2147483654N 4172687047N 917428948N 1164481320N 3805382881N 2998109844N 3500328328N 148530069N 1422642886N 1152959949N 968086223N 2147536338N])
(def testintshare [73 4783830863 6413244902 3348030084 9528735128 3662428236 1179060863 4890373559 2849561951 5105731336 2618884129 5466927911 6046625017 3175759218 3472356969 5150433918 3956919956 341961234 4009712991 5640110705 1])

(fact "Marshalling strings into a sequences of integers"
      (fact "from string to sequence"
            (ms/str2intseq testshare) => testintseq)
      (fact "from sequence to string"
            (ms/intseq2str testintseq) => testshare))

;; (pp/pprint {:compressed (ic/compress testintseq)})
;; (pp/pprint (ic/compress (ic/codec.binpack) testseq))
;; (pp/pprint (conj {:0 "Confirming FastPFOR128 is default, this should return same values"}
;; (ic/compress (ic/codec.pfor128) testintseq)))

(fact "Compressing integers"
      ;; (fact "default codec is PFOR128"
      ;;       (ic/compress testintseq) => (ic/compress
      ;;                                    (ic/codec.pfor128) testintseq))

      (fact "default codec"
            (ic/compress testintseq) => testintcomp
            (ic/decompress testintcomp) => testintseq))
            
            ;; (fact "pfor128 codec"
            ;;       (ic/compress (ic/codec.pfor128) testintseq) => testintcomp
            ;;       (ic/decompress (ic/codec.pfor128) testintcomp) => testintseq)))


(fact "Making all negative integers unsigned"
      (ms/int2unsigned testintcomp) => testuintcomp
      (ms/unsigned2int testuintcomp) => testintcomp)

(fact "Hashing integers"
      (fact "de/code-hash back and forth"
            (ms/decode-hash settings testshare) => testintshare
            (ms/encode-hash settings testintshare) => testshare))
