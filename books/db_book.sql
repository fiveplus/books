/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50512
Source Host           : localhost:3306
Source Database       : db_book

Target Server Type    : MYSQL
Target Server Version : 50512
File Encoding         : 65001

Date: 2017-04-27 15:51:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_group_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_group_permission`;
CREATE TABLE `sys_group_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL,
  `permission_id` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_group_permission
-- ----------------------------
INSERT INTO `sys_group_permission` VALUES ('8', '1', 'handle');
INSERT INTO `sys_group_permission` VALUES ('9', '1', 'user');
INSERT INTO `sys_group_permission` VALUES ('10', '1', 'group');
INSERT INTO `sys_group_permission` VALUES ('11', '1', 'bookType');
INSERT INTO `sys_group_permission` VALUES ('12', '1', 'book');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `parent_id` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_menu` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `class_name` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_index` int(11) DEFAULT NULL,
  `image_url` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `url` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('admin', 'Admin', null, 'N', null, 'Y', '1', null, null, '1440665159555');
INSERT INTO `sys_permission` VALUES ('book', '书籍管理', 'bookManage', 'Y', '', 'Y', '2', '', 'admin/book/list/1', '1486109377557');
INSERT INTO `sys_permission` VALUES ('bookManage', '书籍管理', 'admin', 'Y', 'icon-list', 'Y', '2', '', '', '1485243833792');
INSERT INTO `sys_permission` VALUES ('bookType', '书籍类别', 'bookManage', 'Y', '', 'Y', '1', '', 'admin/booktype/list/1', '1485244107176');
INSERT INTO `sys_permission` VALUES ('group', '组管理', 'main', 'Y', '', 'Y', '3', '', 'admin/group/list/1', '1473391211303');
INSERT INTO `sys_permission` VALUES ('handle', '菜单/权限', 'main', 'Y', '', 'Y', '1', '', 'admin/permission/list/1', '1440666423850');
INSERT INTO `sys_permission` VALUES ('main', '后台管理', 'admin', 'Y', 'icon-desktop', 'Y', '1', '', '', '1440664698162');
INSERT INTO `sys_permission` VALUES ('user', '用户管理', 'main', 'Y', '', 'Y', '2', '', 'admin/user/list/1', '1440665159555');

-- ----------------------------
-- Table structure for sys_user_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_permission`;
CREATE TABLE `sys_user_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_permission
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_advertisement
-- ----------------------------
DROP TABLE IF EXISTS `tbl_advertisement`;
CREATE TABLE `tbl_advertisement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `url` text CHARACTER SET utf8,
  `remark` text CHARACTER SET utf8,
  `create_time` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `image` text CHARACTER SET utf8,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tbl_advertisement
-- ----------------------------
INSERT INTO `tbl_advertisement` VALUES ('1', '秋日读书补元气', '#', '', '1474379961228', 'upload_images/20160923095024.jpg');
INSERT INTO `tbl_advertisement` VALUES ('2', '开学季玩转大学专场', '#', '', '1474595790522', 'upload_images/20160923095627.jpg');
INSERT INTO `tbl_advertisement` VALUES ('3', '新书上线棒棒哒', '#', '', '1474595889406', 'upload_images/20160923095744.jpg');
INSERT INTO `tbl_advertisement` VALUES ('4', '抑郁症至于笔记', '#', '', '1474595918393', 'upload_images/20160923095816.jpg');
INSERT INTO `tbl_advertisement` VALUES ('5', '当时说清楚就好了', '#', '', '1474595955375', 'upload_images/20160923095855.jpg');

-- ----------------------------
-- Table structure for tbl_book
-- ----------------------------
DROP TABLE IF EXISTS `tbl_book`;
CREATE TABLE `tbl_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `author` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `copy_right` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` double(100,0) DEFAULT NULL,
  `public_house` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `public_time` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `info` text COLLATE utf8_unicode_ci,
  `recommend` text COLLATE utf8_unicode_ci,
  `author_info` text COLLATE utf8_unicode_ci,
  `create_time` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `read_count` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `picture` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tbl_book
-- ----------------------------
INSERT INTO `tbl_book` VALUES ('3', '暧昧', '凯伦·罗巴德斯', '', '0', '禁书出版社', '19', '1491321600000', '<div>当贝嘉蓓得到消息,说她异母哥哥因意外去世时, 为了让未出嫁的妹妹们能觅得良缘,她只好宣称哥哥还活著。 然而,当她们来到位於伦敦的伯爵府邸时, 却发现早已有一位英俊、迷人的“伯爵”进驻其中！ 而今,被秘密和谎言重重束缚住的嘉蓓, 又该如何挣脱这团混乱,得到属於自己的良缘呢？</div>', '<span style=\"color: rgb(51, 51, 51); font-family: arial, helvetica, sans-serif; font-size: 14px;\">“罗巴德斯对人物角色具有敏锐把握力，擅长辛辣讽刺与诙谐幽默并举。”</span><br style=\"color: rgb(51, 51, 51); font-family: arial, helvetica, sans-serif; font-size: 14px;\"><span style=\"color: rgb(51, 51, 51); font-family: arial, helvetica, sans-serif; font-size: 14px;\">——《浪漫时代》杂志（Romantic Times Magazine）</span><br>', '<span style=\"color: rgb(51, 51, 51); font-family: arial, helvetica, sans-serif; font-size: 14px;\">凯伦·罗巴德斯（Karen Robards）出生于1957年，是《纽约时报》（New York Times）的畅销作家，著有四十多部作品，24岁时出版自己第一部小说，是当今浪漫小说界最负盛名的作者之一。她曾获由《浪漫时代》杂志（Romantic Times）与《韵事》杂志（Affaire de Coeur）颁发的小说奖项。她的悬疑小说同时登上《纽约时报》、《华尔街日报》和《今日美国》的畅销书榜单，作品曾被翻译成11种语言。</span><br style=\"color: rgb(51, 51, 51); font-family: arial, helvetica, sans-serif; font-size: 14px;\"><span style=\"color: rgb(51, 51, 51); font-family: arial, helvetica, sans-serif; font-size: 14px;\">　　凯伦有一个幸福的家庭，她和丈夫道格（Doug），还有他们的三个孩子一起住在肯塔基州的路易斯维尔市。</span><br>', '1491371310684', '781', 'book_images/nocover.jpg');

-- ----------------------------
-- Table structure for tbl_bookfile
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bookfile`;
CREATE TABLE `tbl_bookfile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `create_time` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tbl_bookfile
-- ----------------------------
INSERT INTO `tbl_bookfile` VALUES ('2', '暧昧.txt', '1491372093026', '3', '1');

-- ----------------------------
-- Table structure for tbl_booktype
-- ----------------------------
DROP TABLE IF EXISTS `tbl_booktype`;
CREATE TABLE `tbl_booktype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `is_main` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tbl_booktype
-- ----------------------------
INSERT INTO `tbl_booktype` VALUES ('2', '期刊', '1473663031844', null, 'N');
INSERT INTO `tbl_booktype` VALUES ('3', '外文', '1473663065475', null, 'N');
INSERT INTO `tbl_booktype` VALUES ('4', '原创女频', '1473663082936', null, 'N');
INSERT INTO `tbl_booktype` VALUES ('5', '原创男频', '1473663094360', null, 'N');
INSERT INTO `tbl_booktype` VALUES ('6', '科技', '1473663102580', null, 'N');
INSERT INTO `tbl_booktype` VALUES ('7', '社会科技', '1473663117366', null, 'N');
INSERT INTO `tbl_booktype` VALUES ('8', '文学艺术', '1473663126935', null, 'N');
INSERT INTO `tbl_booktype` VALUES ('9', '亲子少儿', '1473663137855', null, 'N');
INSERT INTO `tbl_booktype` VALUES ('10', '生活', '1473663144665', null, 'N');
INSERT INTO `tbl_booktype` VALUES ('11', '两性情感', '1473663156080', null, 'N');
INSERT INTO `tbl_booktype` VALUES ('12', '计算机', '1473663163086', null, 'N');
INSERT INTO `tbl_booktype` VALUES ('13', '历史传记', '1473663171908', null, 'N');
INSERT INTO `tbl_booktype` VALUES ('14', '成功励志', '1473663180413', null, 'N');
INSERT INTO `tbl_booktype` VALUES ('15', '经济管理', '1473663190261', null, 'N');
INSERT INTO `tbl_booktype` VALUES ('16', '小说', '1473663199014', null, 'N');
INSERT INTO `tbl_booktype` VALUES ('17', '其他', '1473663236368', '2', 'N');
INSERT INTO `tbl_booktype` VALUES ('18', '其他', '1473663592812', '3', 'N');
INSERT INTO `tbl_booktype` VALUES ('19', '现代言情', '1473663618805', '4', 'N');
INSERT INTO `tbl_booktype` VALUES ('20', '婚恋情感', '1473663633133', '4', 'N');
INSERT INTO `tbl_booktype` VALUES ('21', '古言架空', '1473663655589', '4', 'N');
INSERT INTO `tbl_booktype` VALUES ('22', '穿越重生', '1473663680229', '4', 'N');
INSERT INTO `tbl_booktype` VALUES ('23', '浪漫青春', '1473663702669', '4', 'N');
INSERT INTO `tbl_booktype` VALUES ('24', '武侠仙侠', '1473663719875', '4', 'N');
INSERT INTO `tbl_booktype` VALUES ('25', '幻想异能', '1473663737825', '4', 'N');
INSERT INTO `tbl_booktype` VALUES ('26', '其他', '1473663887835', '4', 'N');
INSERT INTO `tbl_booktype` VALUES ('27', '玄幻奇幻', '1473663903356', '5', 'N');
INSERT INTO `tbl_booktype` VALUES ('28', '武侠仙侠', '1473663932951', '5', 'N');
INSERT INTO `tbl_booktype` VALUES ('29', '都市生活', '1473663949968', '5', 'Y');
INSERT INTO `tbl_booktype` VALUES ('31', '官场商战', '1473663970140', '5', 'N');
INSERT INTO `tbl_booktype` VALUES ('32', '历史架空', '1473664018755', '5', 'N');
INSERT INTO `tbl_booktype` VALUES ('33', '军事谍战', '1473664038826', '5', 'N');
INSERT INTO `tbl_booktype` VALUES ('34', '科幻末日', '1473664063705', '5', 'N');
INSERT INTO `tbl_booktype` VALUES ('35', '灵异悬疑', '1473664080505', '5', 'N');
INSERT INTO `tbl_booktype` VALUES ('36', '游戏竞技', '1473664095369', '5', 'N');
INSERT INTO `tbl_booktype` VALUES ('37', '其他', '1473664105923', '5', 'N');
INSERT INTO `tbl_booktype` VALUES ('38', '科普读物', '1473664131319', '6', 'N');
INSERT INTO `tbl_booktype` VALUES ('39', '自然科学', '1473664145615', '6', 'N');
INSERT INTO `tbl_booktype` VALUES ('40', '医学', '1473664161307', '6', 'N');
INSERT INTO `tbl_booktype` VALUES ('41', '工业技术', '1473664173095', '6', 'N');
INSERT INTO `tbl_booktype` VALUES ('42', '其他', '1473664202124', '6', 'N');
INSERT INTO `tbl_booktype` VALUES ('43', '社会学', '1473664267874', '7', 'N');
INSERT INTO `tbl_booktype` VALUES ('44', '心理学', '1473664280576', '7', 'N');
INSERT INTO `tbl_booktype` VALUES ('45', '宗教哲学', '1473664293450', '7', 'N');
INSERT INTO `tbl_booktype` VALUES ('46', '军事法律', '1473664307674', '7', 'Y');
INSERT INTO `tbl_booktype` VALUES ('47', '时事政治', '1473664322700', '7', 'N');
INSERT INTO `tbl_booktype` VALUES ('48', '文化', '1473664335646', '7', 'N');
INSERT INTO `tbl_booktype` VALUES ('49', '教育考试', '1473664345067', '7', 'Y');
INSERT INTO `tbl_booktype` VALUES ('50', '其他', '1473664356848', '7', 'N');
INSERT INTO `tbl_booktype` VALUES ('51', '散文随笔', '1473664372308', '8', 'N');
INSERT INTO `tbl_booktype` VALUES ('52', '绘本画册', '1473664443306', '8', 'N');
INSERT INTO `tbl_booktype` VALUES ('53', '设计美术', '1473664457393', '8', 'N');
INSERT INTO `tbl_booktype` VALUES ('54', '音乐戏剧', '1473664478432', '8', 'N');
INSERT INTO `tbl_booktype` VALUES ('55', '纪实文学', '1473664521480', '8', 'N');
INSERT INTO `tbl_booktype` VALUES ('56', '文学理论', '1473664535788', '8', 'N');
INSERT INTO `tbl_booktype` VALUES ('57', '诗词歌赋', '1473664554327', '8', 'N');
INSERT INTO `tbl_booktype` VALUES ('58', '国学经典', '1473664565587', '8', 'N');
INSERT INTO `tbl_booktype` VALUES ('59', '世界名著', '1473664584230', '8', 'Y');
INSERT INTO `tbl_booktype` VALUES ('60', '其他', '1473664595740', '8', 'N');
INSERT INTO `tbl_booktype` VALUES ('61', '孕产育儿', '1473664661488', '9', 'N');
INSERT INTO `tbl_booktype` VALUES ('62', '儿童文学', '1473664674756', '9', 'N');
INSERT INTO `tbl_booktype` VALUES ('63', '科普百科', '1473664689487', '9', 'N');
INSERT INTO `tbl_booktype` VALUES ('64', '幼儿启蒙', '1473664720172', '9', 'N');
INSERT INTO `tbl_booktype` VALUES ('65', '烹调饮食', '1473664739073', '10', 'Y');
INSERT INTO `tbl_booktype` VALUES ('66', '健康养生', '1473664753089', '10', 'Y');
INSERT INTO `tbl_booktype` VALUES ('67', '占星风水', '1473664768062', '10', 'Y');
INSERT INTO `tbl_booktype` VALUES ('68', '时尚摄影', '1473664782604', '10', 'N');
INSERT INTO `tbl_booktype` VALUES ('69', '旅游地理', '1473664800906', '10', 'N');
INSERT INTO `tbl_booktype` VALUES ('70', '家居手工', '1473664813889', '10', 'N');
INSERT INTO `tbl_booktype` VALUES ('71', '美容塑身', '1473664837526', '10', 'Y');
INSERT INTO `tbl_booktype` VALUES ('72', '运动健身', '1473664853835', '10', 'N');
INSERT INTO `tbl_booktype` VALUES ('73', '休闲娱乐', '1473664867885', '10', 'N');
INSERT INTO `tbl_booktype` VALUES ('74', '两性关系', '1473664889697', '11', 'N');
INSERT INTO `tbl_booktype` VALUES ('75', '婚姻家庭', '1473664905042', '11', 'N');
INSERT INTO `tbl_booktype` VALUES ('76', '情感恋爱', '1473664915197', '11', 'N');
INSERT INTO `tbl_booktype` VALUES ('77', '软硬件开发', '1473664931812', '12', 'Y');
INSERT INTO `tbl_booktype` VALUES ('78', '图形与图像', '1473664947112', '12', 'N');
INSERT INTO `tbl_booktype` VALUES ('79', '网络与通信', '1473664966567', '12', 'Y');
INSERT INTO `tbl_booktype` VALUES ('80', '家庭与办公', '1473664988032', '12', 'N');
INSERT INTO `tbl_booktype` VALUES ('81', 'IT人文', '1473665006170', '12', 'N');
INSERT INTO `tbl_booktype` VALUES ('82', '其他', '1473665015825', '12', 'N');
INSERT INTO `tbl_booktype` VALUES ('83', '人物传记', '1473665043807', '13', 'N');
INSERT INTO `tbl_booktype` VALUES ('84', '普及读物', '1473665058028', '13', 'N');
INSERT INTO `tbl_booktype` VALUES ('85', '世界各国史', '1473665077491', '13', 'Y');
INSERT INTO `tbl_booktype` VALUES ('86', '中国史', '1473665093339', '13', 'N');
INSERT INTO `tbl_booktype` VALUES ('87', '成功学', '1473665107427', '14', 'Y');
INSERT INTO `tbl_booktype` VALUES ('88', '人在职场', '1473665120145', '14', 'N');
INSERT INTO `tbl_booktype` VALUES ('89', '演讲口才', '1473665137143', '14', 'N');
INSERT INTO `tbl_booktype` VALUES ('90', '人际处事', '1473665161231', '14', 'N');
INSERT INTO `tbl_booktype` VALUES ('91', '心灵修养', '1473665183445', '14', 'Y');
INSERT INTO `tbl_booktype` VALUES ('92', '性格与情绪', '1473665199286', '14', 'N');
INSERT INTO `tbl_booktype` VALUES ('93', '青少年励志', '1473665223206', '14', 'N');
INSERT INTO `tbl_booktype` VALUES ('94', '企业管理', '1473665251697', '15', 'N');
INSERT INTO `tbl_booktype` VALUES ('95', '经济金融', '1473665271189', '15', 'N');
INSERT INTO `tbl_booktype` VALUES ('96', '投资理财', '1473665283304', '15', 'N');
INSERT INTO `tbl_booktype` VALUES ('97', '市场营销', '1473665295710', '15', 'N');
INSERT INTO `tbl_booktype` VALUES ('98', '财会统计', '1473665330804', '15', 'N');
INSERT INTO `tbl_booktype` VALUES ('99', '通俗读物', '1473665346990', '15', 'N');
INSERT INTO `tbl_booktype` VALUES ('100', '言情', '1473665361571', '16', 'N');
INSERT INTO `tbl_booktype` VALUES ('101', '青春', '1473665373031', '16', 'N');
INSERT INTO `tbl_booktype` VALUES ('102', '都市', '1473665382842', '16', 'N');
INSERT INTO `tbl_booktype` VALUES ('103', '历史', '1473665402972', '16', 'N');
INSERT INTO `tbl_booktype` VALUES ('104', '科幻', '1473665416414', '16', 'N');
INSERT INTO `tbl_booktype` VALUES ('105', '军事', '1473665435308', '16', 'Y');
INSERT INTO `tbl_booktype` VALUES ('106', '官场商战', '1473665454674', '16', 'N');
INSERT INTO `tbl_booktype` VALUES ('107', '悬疑推理', '1473665468993', '16', 'Y');
INSERT INTO `tbl_booktype` VALUES ('108', '武侠魔幻', '1473665482185', '16', 'Y');
INSERT INTO `tbl_booktype` VALUES ('109', '灵异恐怖', '1473665497073', '16', 'Y');
INSERT INTO `tbl_booktype` VALUES ('110', '社会乡土', '1473665523982', '16', 'N');
INSERT INTO `tbl_booktype` VALUES ('111', '影视文学', '1473665534043', '16', 'N');
INSERT INTO `tbl_booktype` VALUES ('112', '现当代小说', '1473665542676', '16', 'N');
INSERT INTO `tbl_booktype` VALUES ('113', '外国文学', '1473665556883', '16', 'Y');
INSERT INTO `tbl_booktype` VALUES ('114', '其他', '1473665565597', '16', 'N');

-- ----------------------------
-- Table structure for tbl_group
-- ----------------------------
DROP TABLE IF EXISTS `tbl_group`;
CREATE TABLE `tbl_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tbl_group
-- ----------------------------
INSERT INTO `tbl_group` VALUES ('1', '超级管理', '0', '');

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL,
  `user_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `login_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `portrait` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` text COLLATE utf8_unicode_ci,
  `is_admin` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('1', '1', '系统管理员', 'admin', 'b594510740d2ac4261c1b2fe87850d08', 'upload_images/admin.jpg', '', 'Y', '0');
