(ns fxc.marshalling-test
  (:use midje.sweet)
  (:require [clojure.pprint :as pp]
            [fxc.core :refer :all]
            [fxc.marshalling :as ms]
            [fxc.intcomp     :as ic ]))

(pp/pprint {"------------------------------------------" "MARSHALLING_TESTS"})

;; random share
(def testshare "R9LFNKQYZZN9SDNN5VYM6AK5ZDLKZDFEJQWKRYQAYNW5W6NZFVK952PGB79EQ3LNLBM2JRQNLDHPQ5D62DPTRRZNQE69H2PPJMEY3CGVN9E6R3S95J2ZVPWC5LM78YJESK3YD6VDVF835NW3D2SMGWVE99SJD3NKEVMB7ND8GMZQTZ")
(def testintseq [82 57 76 70 78 75 81 89 90 90 78 57 83 68 78 78 53 86 89 77 54 65 75 53 90 68 76 75 90 68 70 69 74 81 87 75 82 89 81 65 89 78 87 53 87 54 78 90 70 86 75 57 53 50 80 71 66 55 57 69 81 51 76 78 76 66 77 50 74 82 81 78 76 68 72 80 81 53 68 54 50 68 80 84 82 82 90 78 81 69 54 57 72 50 80 80 74 77 69 89 51 67 71 86 78 57 69 54 82 51 83 57 53 74 50 90 86 80 87 67 53 76 77 55 56 89 74 69 83 75 51 89 68 54 86 68 86 70 56 51 53 78 87 51 68 50 83 77 71 87 86 69 57 57 83 74 68 51 78 75 69 86 77 66 55 78 68 56 71 77 90 81 84 90]) ;; feed to compress
(def testintcomp [174 117901063 -388817710 1521698396 624374701 1798675770 772499894 1931631211 -1961220695 695593162 1501775565 -1250511385 -347687623 1100175154 -1369718129 -1657692904 -1504485044 1285375636 -1390800350 573730065 1788160660 765645213 -1589537657 993093322 -827515365 -1658400420 -1523223731 1585802060 -211405433 -1960129658 7 1261233619 1451841972 1935044131 -649828514 1522301364 1423751563 -1757832119 -1026697531 -1195061577 -774189625 56020])
(def testuintcomp [174 2265384710N 1758665937N 3669182043N 2771858348N 3946159417N 2919983541N 4079114858N 186262952N 2843076809N 3649259212N 896972262N 1799796024N 3247658801N 777765518N 489790743N 642998603N 3432859283N 756683297N 2721213712N 3935644307N 2913128860N 557945990N 3140576969N 1319968282N 489083227N 624259916N 3733285707N 1936078214N 187353989N 2147483654N 3408717266N 3599325619N 4082527778N 1497655133N 3669785011N 3571235210N 389651528N 1120786116N 952422070N 1373294022N 2147539667N])
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
