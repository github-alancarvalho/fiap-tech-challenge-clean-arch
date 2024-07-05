package br.com.fiap.techchallenge.fiapfood.frameworksdrivers.api;

import br.com.fiap.techchallenge.fiapfood.__adapters.CategoriaResponse;
import br.com.fiap.techchallenge.fiapfood.interfaces.DbConnection;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Tag(name = "Categoria API")
@RestController
@RequestMapping("/api/v1/CategoriaX")
public class CategoriaApiX {

    @Autowired()
    private DbConnection connection;

    public CategoriaApiX() {
     }


    @Operation(summary = "Buscar todas as categorias", description = "Buscar todas as categorias")
    @GetMapping("/buscarTodasCategorias")
    public ResponseEntity<Optional<List<CategoriaResponse>>> buscarTodasCategorias(DbConnection connection) {

        //CategoriaController controller = new CategoriaController(connection);



            return ResponseEntity.notFound().build();

    }
}

