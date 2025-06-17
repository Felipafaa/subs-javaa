package com.feliperosa.catalogo_livros.specification;

import org.springframework.data.jpa.domain.Specification;

import com.feliperosa.catalogo_livros.entity.Livro;

public class LivroSpecification {

    public static Specification<Livro> tituloContains(String titulo) {
        return (root, query, builder) -> titulo == null ? null
                : builder.like(builder.lower(root.get("titulo")), "%" + titulo.toLowerCase() + "%");
    }

    public static Specification<Livro> autorContains(String autor) {
        return (root, query, builder) -> autor == null ? null
                : builder.like(builder.lower(root.get("autor")), "%" + autor.toLowerCase() + "%");
    }

    public static Specification<Livro> anoPublicacaoBetween(Integer anoInicial, Integer anoFinal) {
        return (root, query, builder) -> {
            if (anoInicial != null && anoFinal != null) {
                return builder.between(root.get("anoPublicacao"), anoInicial, anoFinal);
            } else if (anoInicial != null) {
                return builder.greaterThanOrEqualTo(root.get("anoPublicacao"), anoInicial);
            } else if (anoFinal != null) {
                return builder.lessThanOrEqualTo(root.get("anoPublicacao"), anoFinal);
            }
            return null;
        };
    }
}
