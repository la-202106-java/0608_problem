package la.java.sql;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * JavaとDBの日付回りのテストコード
 *
 * Javaの世界とDBの世界で取り扱うクラスが違います
 *
 * [Java]
 * 年月日: {@link java.time.LocalDate}
 * 日時: {@link java.time.LocalDateTime}
 * 時間: {@link java.time.LocalTime}
 *
 * [DB]
 * 年月日: {@link java.sql.Date}
 * 日時: {@link java.sql.Timestamp}
 * 時間: {@link java.sql.Time}
 *
 * 基本的に、DBに変換する必要があるタイミングでのみ、java.sqlパッケージの各クラスに変換する設計が望ましいです
 * なぜなら外部に依存している影響範囲を最小限に抑えることで不具合が起きた時の調査やコード設計をきれいに担保することができます
 *
 * そのため、java.sqlパッケージはDAOでSQLを発行する時にのみ利用しましょう
 * JavaBeansにはjava.timeパッケージで管理するようにしましょう
 */
class SQLDateTest {

	@Test
	void 時間のフォーマットテスト() {
		String before = "11:00:00";
		String after = "15:00";

		DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");

		LocalTime beforeTime = java.time.LocalTime.parse(before);
		LocalTime afterTime = java.time.LocalTime.parse(after);
		String beforeResult = beforeTime.format(df);
		String afterResult = afterTime.format(df);
		System.out.println(beforeResult);
		System.out.println(afterResult);

		System.out.println(java.sql.Time.valueOf(beforeTime));
		System.out.println(java.sql.Time.valueOf(afterTime));
		System.out.println(java.sql.Time.valueOf(beforeResult));
		System.out.println(java.sql.Time.valueOf(afterResult));
	}

	@Test
	void WebからgetParameterした日付をsqlに変換する() {
		// getParameterしたものと想定
		String targetDate = "2021-06-23"; // 年月日
		// java.sql.Date.valueOfを使うとStringの日付文字列からそのまま変換できる
		java.sql.Date sqlDate = java.sql.Date.valueOf(targetDate);
		assertEquals(sqlDate.toString(), targetDate); // 同じ日付かテスト
	}

	@Test
	void WebからgetParameterした時間をsqlに変換する() {
		// getParameterしたものと想定
		String targetTime = "18:15"; // 時分
		// java.sql.Time.valueOfを使うとStringの時間文字列からそのまま変換できる
		// 時間文字列のフォーマットは"hh:mm:ss"と、秒まで含まれるので注意
		java.sql.Time sqlTime = java.sql.Time.valueOf(targetTime + ":00");
		assertEquals(sqlTime.toString(), targetTime + ":00"); // 同じ時間かテスト
	}

	@Test
	void 現在日をDBへ入れ込む_for_Java() {
		// JavaのAPIを使って現在日を取得するパターン
		// java.time.LocalDateを使用する
		LocalDate now = LocalDate.now();
		java.sql.Date sqlDate = java.sql.Date.valueOf(now);
		assertEquals(sqlDate.toString(), now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))); // 同じ日付かテスト
	}

	@Test
	void 現在日をDBへ入れ込む_for_SQL() {
		// SQL使って現在日を取得するパターン
		// SQLのnow()関数を使用する
		String selectSQL = "SELECT now()";
		String insertSQL = "INSERT INTO table_name (created) VALUES(now())";
	}

	@Test
	void DBから取得した日付をJavaの日付クラスへ変換する() throws SQLException {
		// 日付＝"yyyy-MM-dd"形式

		// このコードはDBにつないでおらず仮実装で実行できないので注意。参考まで
		ResultSet rs = null;
		// getDateするとjava.sql.Date型で返る
		java.sql.Date sqlDate = rs.getDate(1);
		// java.time.LocalDateに変換するにはjava.sql.DateのtoLocalDateを使う
		java.time.LocalDate javaDate = sqlDate.toLocalDate();
		// 基本的にjava.util.Dateではなくjava.time.LocalDateを使おう
		// 理由はJavaベーシック240Pを参照
	}

	@Test
	void DBから取得した日時をJavaの日時クラスへ変換する() throws SQLException {
		// 日時＝"yyyy-MM-dd hh:mm:ss"形式

		// このコードはDBにつないでおらず仮実装で実行できないので注意。参考まで
		ResultSet rs = null;
		// getTimestampするとjava.sql.Timestamp型で返る
		java.sql.Timestamp sqlTimestamp = rs.getTimestamp(1);
		// java.time.LocalDateTimeに変換するにはjava.sql.TimestampのtoLocalDateTimeを使う
		java.time.LocalDateTime javaDateTime = sqlTimestamp.toLocalDateTime();
	}

	@Test
	void DBから取得した時間をJavaの時間クラスへ変換する() throws SQLException {
		// 時間＝"hh:mm:ss"形式

		// このコードはDBにつないでおらず仮実装で実行できないので注意。参考まで
		ResultSet rs = null;
		// getTimeするとjava.sql.Time型で返る
		java.sql.Time sqlTime = rs.getTime(1);
		// java.time.LocalTimeに変換するにはjava.sql.TimeのtoLocalTimeを使う
		java.time.LocalTime javaTime = sqlTime.toLocalTime();
	}

}
