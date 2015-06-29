package restdb.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.annotations.UuidGenerator;


/**
 * The persistent class for the cards database table.
 * 
 */
@UuidGenerator(name="UUID")
@XmlRootElement
@Entity
@Table(name="cards")
@NamedQuery(name="Card.findAll", query="SELECT c FROM Card c")
public class Card implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="UUID")
	private String id;

	@Lob
	private String dtAnswer;

	@Lob
	private String dtHint;

	@Lob
	private String dtQuestion;

	public Card() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDtAnswer() {
		return this.dtAnswer;
	}

	public void setDtAnswer(String dtAnswer) {
		this.dtAnswer = dtAnswer;
	}

	public String getDtHint() {
		return this.dtHint;
	}

	public void setDtHint(String dtHint) {
		this.dtHint = dtHint;
	}

	public String getDtQuestion() {
		return this.dtQuestion;
	}

	public void setDtQuestion(String dtQuestion) {
		this.dtQuestion = dtQuestion;
	}

}