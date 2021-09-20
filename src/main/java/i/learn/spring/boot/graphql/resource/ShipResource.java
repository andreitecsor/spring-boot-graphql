package i.learn.spring.boot.graphql.resource;

import graphql.ExecutionResult;
import i.learn.spring.boot.graphql.service.GraphQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/ships")
@RestController
public class ShipResource {

    @Autowired
    private GraphQLService graphQLService;

    @PostMapping
    public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
        ExecutionResult execute = graphQLService.getGraphQl().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}
