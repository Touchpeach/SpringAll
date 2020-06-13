package com.springboot.demo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.db.handler.EntityListHandler;
import cn.hutool.db.sql.SqlExecutor;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create pengtao  过滤导出车辆没有车辆信息的车辆
 **/
public class getCarId {

    @Test
    public void getCarIdTest() throws SQLException {
        //String vins = "LS4ADM3C0HF056391,LS4ADM3C6HF058355,LS4ADM3C9HF057362,LS4ADM3C5HF057312,LS4ADM3C7HF056324,LS4ADM3C2HF057090,LS4ADM3C7HF057425,LS4ADM3C6HF057349,LS4ADM3C3HF056126,LS4ADM3C7HF056386,LS4ADM3C7HF056338,LS4ADM3C0HF063891,LS4ADM3CXHF064028,LS4ADM3C4HF058032,LS4ADM3CXHF056608,LS4ADM3C3HF053808,LS4ADM3C0HF058741,LS4ADM3C2HF056117,LS4ADM3C3HF057096,LS4ADM3C5HF057326,LS4ADM3C9HF057314,LS4ADM3C8HF056851,LS4ADM3C8HF064660,LS4ADM2C2JF009421,LS4ADM3C8HF058664,LS4ADM3C5HF058430,LS4ADM3C9HF058270,LS4ADM3C0HF058268,LS4ADM3CXHF058679,LS4ADM3C9HF058284,LS4ADM3C2HF058403,LS4ADM3C4HF058421,LS4ADM3CXHF058276,LS4ADM3C9HF058267,LS4ADM3C6HF058260,FMT-CE21708050245,FMT-CE21708050246,LS4ADM3C2HF058529,LS4ADM3C5HF064616,LS4ADM3C4HF057463,LS4ADM3C1HF059915,LS4ADM3C5HF057505,LS4ADM3C4HF057625,LS4ADM3C5HF057651,LS4ADM3C9HF054624,LS4ADM2C8HF055815,LS4ADM3C1HF057467,LS4ADM3C0HF055662,LS4ADM3C7HF054427,LS4ADM3C3HF054618,LS4ADM3C1HF057081,LS4ADM3C4HF056703,LS4ADM3C9HF056891,LS4ADM3C0HF054284,LS4ADM3C7HF058509,LS4ADM3C4HF058743,LS4ADM3C5HF058802,LS4ADM3C3HF060631,LS4ADM3C8HF061242,LS4ADM3C6HF060753,LS4ADM3C4HF062002,LS4ADM3C3HF061682,LS4ADM3CXHF064112,LS4ADM3C2HF064136,LS4ADM3C8HF064089,LS4ADM3C1HF053547,LS4ADM3C8HF056686,FMT-CE21708050768,LS4ADM3C9HF064523,LS4ADM3C3HF059379,LS4ADM3C9HF064389,LS4ADM3C1HF057761,LS4ADM3C9HF057930,LS4ADM3CXHF056379,LS4ADM3CXHF057953,LS4ADM3C6HF057352,LS4ADM3C2HF057767,LS4ADM3C8HF056140,LS4ADM3CXHF057337,LS4ADM2C9JF008959,LS4ADM2C8JF008998,LS4ADM2C1JF012665,LS4ADM2C4JF009615,LS4ADM2CXJF009456,LS4ADM2C5JF009445,LS4ADM2C1JF008079,LS4ADM2C7JF009544,LS4ADM2C1JF009135,LS4ADM2CXJF008811,LS4ADM2C7JF009396,LS4ADM2CXJF012132,FMT-CE21801160207,LS4ADM2C1JF012763,FMT-CE21801160209,LS4ADM2C1JF009524,LS4ADM2C2JF012903,LS4ADM2C4JF009307,LS4ASE2C1JF008241,LS4ASE2C1JF800034,LS4ADM2C0JF009482,LS4ADM2C0JF009384,LS4ADM2C2JF008978,LS4ADM2C3JF009413,LS4ADM2C2JF009046,LS4ADM2C0JF009062,LS4ADM2C5JF009364,LS4ADM2C8JF008063,LS4ADM2C1JF009474,LS4ADM2CXJF009182,LS4ADM2C9JF009447,LS4ADM3C5HF051798,LS4ADM2C0JF008817,LS4ADM2C8JF009536,LS4ADM2C0JF009515,LS4ADM2C7JF009561,LSCAB52C4JG212501,LS4ADM2C9JF012736,LS4ADM2C9JF012722,LS4ADM2C1JF012794,LS4ADM2C6JF012919,LS4ADM2C0JF012947,LS4ADM2C4JF009081,LS4ADM2C9JF012932,LS4ADM2C2JF012920,LS4ADM2C0JF008090,LS4ADM2C1JF012732,LS4ADM2CXJF008145,LS4ADM2C4JF008142,LS4ADM2C9JF008105,LS4ADM2C9JF008136,LS4ADM2C7JF012735,LS4ADM2C4JF009405,LS4ADM2C7JF011195,LS4ADM2C0JF008834,LS4ADM2C8JF009407,LS4ADM2C0JF009546,LS4ADM2CXJF012664,LS4ADM2C0JF008820,LS4ADM2C7JF011200,LS4ADM2C4JF008089,LS4ADM2C2JF009371,LS4ADM2C1JF012827,LS4ADM2C4JF012790,LS6C3E0C8KF014121,LS4ADM2C1JF014013,LS4ADM2C4JF009131,LS4ADM2C6JF009406,LS4ADM2C0JF009630,LS4ADM2C9JF009612,LS4ADM2C8JF012789,LS4ADM2CXJF009540,LS4ADM2C1JF009569,LS4ADM2CXJF009568,LS4ADM2C1JF008101,LS4ADM2C9JF008847,LS4ADM2C5JF008103,LS4ADM2C2JF009323,LS4ADM2C7JF009351,LS4ADM2C8JF016339,LS4ADM2C9JF008850,LS4ADM2C7JF008149,LS4ADM2CXJF008064,LS4ADM2C4JF009274,LS4ADM2C0JF012706,LS4ADM2C6JF008966,LS4ADM2C0JF009532,LS4ADM2C4JF012692,LS4ADM2C0JF009353,LS4ADM2C0JF009417,LS4ADM2CXJF009425,LS4ADM2C5JF009428,FMT-CE30B03221026,LS4ADM2C7JF012881,LS4ADM2C2JF012822,LS4ADM2C8JF008144,LS4ADM2C3JF008956,LS4ADM2C8JF009567,LS4ADM2CXJF012907,LS4ADM2C3JF016233,LS4ADM2C2JF009418,LS4ADM2C7JF009611,LS4ADM2C6JF009342,LS4ADM2C4JF009503,LS4ADM2C5JF009171,LS4ADM2C1JF016442,LS4ADM2CXJF016231,LS4ADM2C3JF008147,LS4ADM2C8JF008130,LS4ADM2C9JF011201,LS4ADM2C1JF009572,LS4ADM2C5JF008151,LS4ADM2C2JF009290,FMT-CE21801300069,FMT-CE21801300014,LS4ADM2C3JF013848,LS4ADM2CXJF013751,LS4ADM2C0JF009448,LS6C3E0C4KF015458,LS4ADM2C9JF013692,LS4ADM2C8JF009276,LS4ADM2C5JF012684,LS4ADM2C0JF013760,LS4ADM2C6JF009518,LS4ADM2CXJF012695,LS4ADM2C0JF012723,LS4ADM2C8JF012730,LS4ADM2C4JF012840,LS4ADM2C2JF011203,LS4ADM2C5JF016296,LS4ADM2C3JF016197,LS4ADM2C1JF012679,LS4ASE2C3JF010587,LS4ASE2C2JF010063,LS4ASE2C3JF010489,LS4ASE2C5JF009036,LS4ASE2C0JF009798,LS4ASE2C8JF009659,LS4ASE2C1JF009583,LS4ASE2C6JF009837,LS4ASE2C7JF010463,LS4ASE2C4JF009898,LS4ASE2C5JF009991,LS4ASE2C6JF009904,LS4ASE2C0JF009834,LS4ASE2C9JF009847,LS4ASE2C8JF009774,LS4ASE2C5JF009943,LS4ASE2C0JF009784,LS4ASE2C1JF009986,LS4ASE2CXJF010053,LS4ASE2C2JF009897,LS4ADM2C3JF009492,LS4ADM2C7JF008961,LS4ASE2C7JF010611,LS4ASE2C2JF009589,LS4ASE2C1JF009602,LS4ASE2C9JF009699,LS4ASE2C1JF009731,LS4ADM2C0JF009529,LS4ASE2C6JF010079,LS4ADM2C6JF018154,LS4ASE2CXJF010456,LS4ASE2C4JF010551,LS4ASE2C7JF010608,LS4ASE2C0JF009686,LS4ASE2C1JF010460,LS4ASE2C1JF010295,LS4ASE2C7JF010334,LS4ASE2C2JF010256,LS4ASE2CXJF010375,LS4ASE2CXJF010327,FMT-CE30B03224197,LS4ASE2C5JF010459,LS4ASE2CXJF010182,LS4ASE2C8JF010228,LS4ASE2C0JF009980,LS4ASE2C4JF010159,LS4ASE2C9JF009587,LS4ASE2C5JF009828,LS4ASE2C2JF009608,LS4ASE2C3JF009830,LS4ASE2C8JF009967,LS4ASE2C3JF010220,LS4ASE2C1JF010605,LS4ASE2C6JF010146,LS4ASE2C6JF010311,LS4ASE2C1JF010474,LS4ASE2C2JF010483,LS4ASE2C1JF010586,LS4ASE2C7JF010110,LS4ASE2C9JF010142,LS4ASE2C0JF010207,LS4ASE2C4JF010310,LS4ASE2C1JF009955,LS4ASE2C7JF009992,LS4ASE2C3JF010279,LS4ASE2CXJF010134,LS4ASE2C7JF010091,LS4ASE2C8JF010004,LS4ASE2C5JF010302,LS4ASE2C8JF010312,LS4ASE2C1JF010006,LS4ASE2C1JF010233,LS4ASE2C7JF010009,LS4ASE2C4JF010341,LS4ASE2CXJF010036,LS4ASE2C2JF010208,LS4ASE2C6JF010177,LS4ASE2C1JF010281,LS4ASE2C7JF010253,LS4ASE2C3JF010427,LS4ASE2C0JF010286,LS4ASE2C7JF010527,LS4ASE2C1JF010362,LS4ASE2C2JF010192,LS4ASE2C9JF010495,LS4ASE2C5JF009750,LS4ASE2C8JF010570,LS4ASE2C0JF010191,LS4ASE2C1JF009826,LS4ASE2C8JF009709,LS4ASE2C7JF010351,LS4ASE2CXJF010411,LS4ASE2C3JF010556,LS4ASE2C4JF010405,LS4ASE2C3JF010301,LS4ASE2C2JF010001,LS4ASE2C0JF010367,LS4ASE2C1JF010345,LS4ASE2C2JF010404,LS4ASE2C1JF010412,LS4ASE2C4JF010369,LS4ASE2C9JF010531,LS4ASE2C7JF009989,LS4ASE2C8JF010049,LS4ASE2CXJF010067,LS4ASE2C2JF010239,LS4ASE2CXJF010506,LS4ASE2C6JF010549,LS4ASE2C2JF010340,LS4ASE2C8JF010486,LS4ASE2C7JF010561,LS4ASE2C7JF010530,LS4ASE2C3JF010069,LS4ASE2C9JF009752,LS4ASE2C6JF009708,LS4ASE2C0JF010496,LS4ASE2C4JF009870,LS4ASE2C4JF010114,LS4ASE2CXJF010392,LS4ASE2C3JF010511,LS4ASE2C6JF010602,LS4ASE2C7JF008180,LS4ASE2C5JF010249,LS4ASE2C0JF009817,LS4ASE2CXJF009999,LS4ASE2C9JF010206,LS4ASE2C7JF010222,LS4ASE2C7JF010138,LS4ASE2C5JF010445,LS6C3E0C3JF800052,LS4ASE2C9JF009959,LS4ASE2C9JF010576,LS4ASE2C8JF010309,LS4ASE2C7JF010088,LS4ASE2C5JF010073,LS4ASE2C1JF009860,LS4ASE2C5JF010056,LS4ASE2C3JF010329,LS4ASE2C1JF010037,LS4ASE2C7JF009748,LS4ASE2C9JF010237,LS4ASE2C9JF009590,LS4ASE2C9JF010075,LS4ASE2C2JF010130,LS4ASE2C7JF010060,LS4ASE2C3JF009598,LS4ASE2C8JF009600,LS4ASE2C0JF009669,LS4ASE2C1JF010331,LS4ASE2C8JF010343,LS4ASE2C4JF010291,LS4ASE2C9JF010044,LS4ASE2C4JF010260,LS4ASE2C7JF010544,LS4ASE2C9JF010299,LS4ASE2C1JF009714,LS4ASE2C4JF010050,LS4ASE2C3JF010444,LS4ASE2C4JF010419,LS4ASE2C9JF010058,LS4ASE2C4JF009884,LS4ASE2C4JF009822,LS4ASE2C9JF010190,LS4ASE2C5JF010316,LS4ASE2C6JF010115,LS4ASE2C1JF010202,LS4ASE2C5JF010185,LS4ASE2C8JF010116,LS4ASE2C0JF009932,LS4ASE2CXJF009727,LS4ASE2C8JF009936,LS4ASE2C4JF010002,LS4ASE2C2JF009852,LS4ASE2C0JF009963,LS4ASE2C6JF009871,LS4ASE2C4JF009951,LS4ASE2C7JF009927,LS4ASE2C6JF010194,LS4ASE2C6JF010180,LS4ASE2C8JF009970,LS4ASE2C7JF009913,LS4ASE2C5JF009697,LS4ASE2C7JF009961,LS4ASE2C4JF009903,LS4ASE2C8JF009984,LS4ASE2C0JF009896,LS4ASE2C2JF009818,LS4ASE2C5JF010171,LS4ASE2C4JF009741,LS4ASE2C3JF009892,LS4ASE2C9JF009749,LS4ASE2C6JF009742,LS4ASE2C3JF010184,LS4ASE2C2JF009737,LS4ASE2C2JF009978,LS4ASE2C8JF009919,LS4ASE2C8JF009791,LS4ASE2C9JF010416,LS4ASE2C3JF010170,LS4ASE2C3JF010086,LS4ASE2C3JF009990,LS4ASE2CXJF009758,LS4ASE2C3JF010413,LS4ASE2C1JF010488,LS4ASE2C2JF010354,LS4ASE2C3JF010394,LS4ASE2C2JF010399,LS4ASE2C1JF010555,LS4ASE2C5JF010588,LS4ASE2C3JF010203,LS4ASE2C5JF010560,LS4ASE2C3JF009682,LS4ASE2C1JF009034,LS4ASE2C2JF010435,LS4ASE2C1JF009972,LS4ASE2C5JF010364,LS4ASE2C1JF010068,LS4ASE2C5JF009795,LS4ASE2C2JF009849,LS4ASE2C7JF009717,LS4ASE2C2JF009821,LS4ASE2C9JF010089,LS4ASE2C9JF009783,LS4ASE2CXJF009839,LS4ASE2C1JF009809,LS4ASE2C7JF010589,LS4ASE2C5JF009831,LS4ASE2C5JF009988,LS4ASE2C0JF009848,LS4ASE2CXJF010425,LS4ASE2C4JF009738,LS4ASE2C9JF009931,LS4ASE2CXJF009744,LS4ASE2C2JF010080,LS4ASE2C7JF010432,LS4ASE2C3JF010024,LS4ASE2CXJF010487,LS4ASE2C5JF010462,LS4ASE2C0JF010403,LS4ASE2C0JF010515,LS4ASE2C9JF010092,LS4ASE2C6JF010048,LS4ASE2C7JF010141,LS4ASE2C6JF009899,LS4ASE2C4JF010064,LS4ASE2C8JF010035,LS4ASE2C3JF010041,LS4ASE2C9JF009945,LS4ASE2CXJF010120,LS4ASE2C7JF010513,LS4ASE2C4JF010131,LS4ASE2C9JF010593,LS4ASE2C1JF010278,LS4ASE2C2JF009687,LS4ASE2C2JF010421,LS4ASE2C1JF010376,LS4ASE2C1JF009891,LS4ASE2COJF009865,LS4ASE2C2JF009866,LS4ASE2C3JF009813,LS4ASE2C1JF009793,LS4ASE2C5JF009862,LS4ASE2C8JF010584,LS4ASE2C0JF009672,LS4ASE2C3JF009035,LS4ASE2C0JF010417,LS4ASE2C4JF010422,LS4ASE2C5JF010011,LS4ASE2C9JF010173,LS4ASE2CXJF010019,LS4ASE2C1JF010328,LS4ASE2C6JF009949,LS4ASE2C4JF009660,LS4ASE2C6JF009658,LS4ASE2C7JF010107,LS4ASE2C7JF010155";
        String vins = "FMT-CE21708050058,FMT-CE21708050040,FMT-CE21708050038,FMT-CE21708050459,FMT-CE21708050449,FMT-CE21708050458,FMT-CE21708050462,FMT-CE21708050453,FMT-CE21708050451,FMT-CE21708050067,FMT-CE21708050069,FMT-CE21708050073,FMT-CE21708050761,FMT-CE21708050760,FMT-CE21708050759,FMT-CE21708050110,FMT-CE21708050066,FMT-CE21708050072,FMT-CE21708050875,FMT-CE21708050068,FMT-CE21708050896,FMT-CE21708050450,FMT-CE21708050447,FMT-CE21708050444,FMT-CE21708050249,FMT-CE21708050018,FMT-CE21708050015,FMT-CE21708050023,FMT-CE21708050026,FMT-CE21708050022,FMT-CE21708050019,FMT-CE21708050010,FMT-CE21708050028,FMT-CE21708050024,FMT-CE21708050025,FMT-CE21708050021,FMT-CE21708050016,FMT-CE21708050245,FMT-CE21708050246,FMT-CE21708050013,FMT-CE21706300514,FMT-CE21706300530,FMT-CE21706300513,FMT-CE21706300518,FMT-CE21706300532,FMT-CE21706300527,FMT-CE21706300536,FMT-CE21706300506,FMT-CE21706300516,FMT-CE21706300515,FMT-CE21706300366,FMT-CE21706300365,FMT-CE21706300521,FMT-CE21706300355,FMT-CE21706300354,FMT-CE21706300367,FMT-CE21706300001,FMT-CE21706300003,FMT-CE21706300002,FMT-CE21709190863,FMT-CE21709190850,FMT-CE21709190714,FMT-CE21709190712,FMT-CE21709190898,FMT-CE21708050606,FMT-CE21708050613,FMT-CE21708050653,FMT-CE21708050990,FMT-CE21708050711,FMT-CE21708050768,FMT-CE21708050795,FMT-CE21708050793,FMT-CE21708050773,FMT-CE21708050508,FMT-CE21708050360,FMT-CE21708050879,FMT-CE21708050275,FMT-CE21708050385,FMT-CE21708050284,FMT-CE21708050047,FMT-CE21708050464,FMT-CE21801300437,FMT-CE21801300514,FMT-CE21801300525,FMT-CE21801300521,FMT-CE21801300580,FMT-CE21801300607,FMT-CE21801300402,FMT-CE21801300424,FMT-CE21801300433,FMT-CE21801300578,FMT-CE21801300591,FMT-CE21801300553,FMT-CE21801160207,FMT-CE21804260020,FMT-CE21801160209,FMT-CE21801160186,FMT-CE21801300649,FMT-CE21801160190,FMT-CE21801160196,FMT-CE21801160087,FMT-CE21803230041,FMT-CE21803220001,FMT-CE21801300645,FMT-CE21801300634,FMT-CE21801300635,FMT-CE21801300651,FMT-CE21801300604,FMT-CE21801160093,FMT-CE21801300058,FMT-CE21801300479,FMT-CE21801300066,FMT-CE21801300347,FMT-CE21801300359,FMT-CE21801300229,FMT-CE21801300176,FMT-CE21801300174,FMT-CE21801300186,FMT-CE21801300177,FMT-CE21801300164,FMT-CE21801300217,FMT-CE21801300247,FMT-CE21801300490,FMT-CE21801300152,FMT-CE21801300146,FMT-CE21801300342,FMT-CE21801300150,FMT-CE21801300159,FMT-CE21801300136,FMT-CE21801300154,FMT-CE21801300156,FMT-CE21801300134,FMT-CE21801300129,FMT-CE21801300128,FMT-CE21801300220,FMT-CE21801300248,FMT-CE21801300245,FMT-CE21801300328,FMT-CE21801300327,FMT-CE21801300117,FMT-CE21801300249,FMT-CE21801300098,FMT-CE21801300080,FMT-CE21801300090,FMT-CE21801300144,FMT-CE21801300157,FMT-CE21801300054,FMT-CE21801300293,FMT-CE21801300306,FMT-CE30B03220353,FMT-CE21801300148,FMT-CE21801300079,FMT-CE21801300113,FMT-CE21801300192,FMT-CE21801300195,FMT-CE21801300209,FMT-CE21801300432,FMT-CE21801300202,FMT-CE21801300190,FMT-CE21801300381,FMT-CE21801300068,FMT-CE21801300383,FMT-CE21801300353,FMT-CE21801300263,FMT-CE21801300326,FMT-CE21801300351,FMT-CE21801300373,FMT-CE21801300393,FMT-CE21801300329,FMT-CE21801300223,FMT-CE21801300418,FMT-CE21801300419,FMT-CE21801300415,FMT-CE21801300274,FMT-CE21801300255,FMT-CE21801300266,FMT-CE21801300453,FMT-CE21801300123,FMT-CE30B03221026,FMT-CE21801300294,FMT-CE21801300302,FMT-CE21801300131,FMT-CE21801300507,FMT-CE21801300504,FMT-CE21801300295,FMT-CE21801300278,FMT-CE21801300283,FMT-CE21801300268,FMT-CE21801300279,FMT-CE21801300410,FMT-CE21801300033,FMT-CE21801300046,FMT-CE21801300325,FMT-CE21801300367,FMT-CE21801300400,FMT-CE21801300406,FMT-CE21801300043,FMT-CE21801300408,FMT-CE21801300375,FMT-CE21801300350,FMT-CE21801300069,FMT-CE21801300014,FMT-CE21801300015,FMT-CE21801300006,FMT-CE21801300060,FMT-CE30B03220336,FMT-CE21801300008,FMT-CE21801300031,FMT-CE21801300234,FMT-CE21801300022,FMT-CE21801300339,FMT-CE21801300564,FMT-CE21801300569,FMT-CE21801300547,FMT-CE21801300545,FMT-CE21801300540,FMT-CE21801300541,FMT-CE21801300323,FMT-CE21801300324,FMT-CE21801300552,FMT-CE21804180826,FMT-CE21804180899,FMT-CE21804180833,FMT-CE21804180977,FMT-CE21804181063,FMT-CE21804180983,FMT-CE21804180985,FMT-CE21804180998,FMT-CE21804181024,FMT-CE21804181158,FMT-CE21804181134,FMT-CE21804181133,FMT-CE21804180995,FMT-CE21804180990,FMT-CE21804181128,FMT-CE21804181137,FMT-CE21804181139,FMT-CE21804181130,FMT-CE21804181129,FMT-CE21804181159,FMT-CE21804260085,FMT-CE21804260086,FMT-CE21804180959,FMT-CE21804180976,FMT-CE21804180974,FMT-CE21804180981,FMT-CE21804181200,FMT-CE21804260088,FMT-CE21804181141,FMT-CE21804260098,FMT-CE21804180839,FMT-CE21804180845,FMT-CE21804180819,FMT-CE21804180842,FMT-CE21804180817,FMT-CE21804180840,FMT-CE21804180836,FMT-CE21804180865,FMT-CE21804181015,FMT-CE21804181006,FMT-CE21804180876,FMT-CE30B03224197,FMT-CE21804180696,FMT-CE21804180713,FMT-CE21804181122,FMT-CE21804180511,FMT-CE21804181101,FMT-CE21804180642,FMT-CE21804180645,FMT-CE21804180647,FMT-CE21804180650,FMT-CE21804180667,FMT-CE21804180733,FMT-CE21804180676,FMT-CE21804181038,FMT-CE21804181039,FMT-CE21804181029,FMT-CE21804181031,FMT-CE21804181032,FMT-CE21804181095,FMT-CE21804181080,FMT-CE21804181121,FMT-CE21804181071,FMT-CE21804181047,FMT-CE21804181213,FMT-CE21804181111,FMT-CE21804181088,FMT-CE21804180701,FMT-CE21804180585,FMT-CE21804180580,FMT-CE21804180607,FMT-CE21804180113,FMT-CE21804180100,FMT-CE21804180601,FMT-CE21804180600,FMT-CE21804180605,FMT-CE21804180599,FMT-CE21804180597,FMT-CE21804181115,FMT-CE21804181112,FMT-CE21804180119,FMT-CE21804180120,FMT-CE21804180137,FMT-CE21804180010,FMT-CE21804180012,FMT-CE21804180009,FMT-CE21804180486,FMT-CE21804180011,FMT-CE21804180503,FMT-CE21804180211,FMT-CE21804180499,FMT-CE21804180495,FMT-CE21804180502,FMT-CE21804180205,FMT-CE21804180242,FMT-CE21804180208,FMT-CE21804180212,FMT-CE21804180194,FMT-CE21804180216,FMT-CE21804180200,FMT-CE21804180317,FMT-CE21804180332,FMT-CE21804180335,FMT-CE21804180329,FMT-CE21804180385,FMT-CE21804180013,FMT-CE21804180206,FMT-CE21804180294,FMT-CE21804180330,FMT-CE21804180110,FMT-CE21804180142,FMT-CE21804180139,FMT-CE21804180025,FMT-CE21804180140,FMT-CE21804180017,FMT-CE21804180170,FMT-CE21804180184,FMT-CE21804180655,FMT-CE21804180668,FMT-CE21804180691,FMT-CE21804180663,FMT-CE21804180727,FMT-CE21804180685,FMT-CE21804180689,FMT-CE21804180688,FMT-CE21804180724,FMT-CE21804180754,FMT-CE21804180752,FMT-CE21804180751,FMT-CE21804180742,FMT-CE21804180756,FMT-CE21804180722,FMT-CE21804180675,FMT-CE21804180661,FMT-CE21804180666,FMT-CE21804180179,FMT-CE21804180270,FMT-CE21804180278,FMT-CE21804180282,FMT-CE21804180307,FMT-CE21804180496,FMT-CE21804180186,FMT-CE21804180190,FMT-CE21804180191,FMT-CE21804180505,FMT-CE21804180220,FMT-CE21804180497,FMT-CE21804180487,FMT-CE21804180712,FMT-CE21804180308,FMT-CE21804180494,FMT-CE21804180269,FMT-CE21804180267,FMT-CE21804180123,FMT-CE21804180295,FMT-CE21804180310,FMT-CE21804180311,FMT-CE21804180275,FMT-CE21804180290,FMT-CE21804180272,FMT-CE21804180276,FMT-CE21804180266,FMT-CE21804180319,FMT-CE21804180321,FMT-CE21804180180,FMT-CE21804180230,FMT-CE21804180557,FMT-CE21804180555,FMT-CE21804180217,FMT-CE21804180234,FMT-CE21804180459,FMT-CE21804181058,FMT-CE21804181057,FMT-CE21804181064,FMT-CE21804181194,FMT-CE21804181210,FMT-CE21804180573,FMT-CE21804180589,FMT-CE21804180568,FMT-CE21804180577,FMT-CE21804180561,FMT-CE21804180565,FMT-CE21804180564,FMT-CE21804180578,FMT-CE21804180579,FMT-CE21804180515,FMT-CE21804181208,FMT-CE21804181214,FMT-CE21804181215,FMT-CE21804181204,FMT-CE21804181202,FMT-CE21804181207,FMT-CE21804181195,FMT-CE21804181211,FMT-CE21804181056,FMT-CE21804181186,FMT-CE21804181184,FMT-CE21804181180,FMT-CE21804181156,FMT-CE21804181070,FMT-CE21804181152,FMT-CE21804181147,FMT-CE21804181182,FMT-CE21804181179,FMT-CE21804180391,FMT-CE21804180076,FMT-CE21804180099,FMT-CE21804180105,FMT-CE21804181144,FMT-CE21804180368,FMT-CE21804180150,FMT-CE21804180367,FMT-CE21804180387,FMT-CE21804180370,FMT-CE21804180154,FMT-CE21804180151,FMT-CE21804180093,FMT-CE21804180162,FMT-CE21804180091,FMT-CE21804180092,FMT-CE21804180381,FMT-CE21804180508,FMT-CE21804180203,FMT-CE21804180193,FMT-CE21804180251,FMT-CE21804180248,FMT-CE21804180258,FMT-CE21804180252,FMT-CE21804180478,FMT-CE21804180476,FMT-CE21804180247,FMT-CE21804180226,FMT-CE21804180144,FMT-CE21804180245,FMT-CE21804180197,FMT-CE21804180254,FMT-CE21804180199,FMT-CE21804180464,FMT-CE21804180469,FMT-CE21804180463,FMT-CE21804180474,FMT-CE21804180380,FMT-CE21804180122,FMT-CE21804180129,FMT-CE21804180374,FMT-CE21804180236,FMT-CE21804180240,FMT-CE21804180717,FMT-CE21804180709,FMT-CE21804180719,FMT-CE21804181171,FMT-CE21804181107,FMT-CE21804181118,FMT-CE21804181119,FMT-CE21804181110,FMT-CE21804181105,FMT-CE21804180699,FMT-CE21804180715,FMT-CE21804180824,FMT-CE21804181013,FMT-CE21804180808,FMT-CE21804180827,FMT-CE21804180183,FMT-CE21804180165,FMT-CE21804180780,FMT-CE21804180772,FMT-CE21804180771,FMT-CE21804180164,FMT-CE21804180765,FMT-CE21804180766,FMT-CE21804180769,FMT-CE21804180831,FMT-CE21804180814,FMT-CE21804180799,FMT-CE21804180166,FMT-CE21804180161,FMT-CE21804180047,FMT-CE21804180074,FMT-CE21804180067,FMT-CE21804180044,FMT-CE21804180061,FMT-CE21804180086,FMT-CE21804180089,FMT-CE21804180049,FMT-CE21804180069";
        DataSource ds1 = DSFactory.get("group_db1");
        Connection connection = ds1.getConnection();

        List<String> vinList = new ArrayList<>();
        for (String s : vins.split(",")) {
            vinList.add(s);
        }

        List<String> retList = new ArrayList<>();
        List<Entity> entityList;
        String ret = "";

        //for (String vin : vinList) {
            entityList = SqlExecutor.query(connection, "select id,vin,create_time,update_time,terminal_sn from cm_vehicle_info "  , new EntityListHandler());  //where vin = \""+ vin + "\" or terminal_sn = \""+ vin + "\" limit 1"

        for (Entity entity : entityList) {
            try {
                //System.out.println("结果" + entityList.get(0).get("id"));
                //ret = entityList.get(0).get("vin").toString();
                retList.add(entity.get("vin").toString() + "  " + entity.get("terminal_sn").toString()+ "-创建时间：" + entity.get("create_time") + "=====首次登陆时间" + entity.get("update_time"));
            }catch (Exception e){
                e.toString();
            }
        }


        //}


        for (String s : retList) {
            System.out.println(s);

        }






    }
}
