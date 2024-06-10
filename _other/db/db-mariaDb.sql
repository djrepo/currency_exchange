DROP TABLE IF EXISTS trade.quote;

CREATE TABLE trade.quote (
                             id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             unixnanotime bigint,
                             bid bigint,
                             ask bigint,
                             bidVolume bigint,
                             askVolume bigint
) DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;