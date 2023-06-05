package com.lamppit.vitrine.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "user_system", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 4358313336091674789L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 3, message = "{field.size}")
	@NotNull(message = "{field.notnull}")
	@NotEmpty(message = "{field.notempty}")
	@Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$", message = "{field.pattern.notNumber}")
	private String name;

	@Email(message = "{field.email}")
	@NotNull(message = "{field.notnull}")
	private String email;

	@NotNull(message = "{field.notnull}")
	@NotEmpty(message = "{field.notempty}")
	private String username;

	@NotNull(message = "{field.notnull}")
	private String password;

}
