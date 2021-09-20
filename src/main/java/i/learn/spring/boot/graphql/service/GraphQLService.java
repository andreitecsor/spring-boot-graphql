package i.learn.spring.boot.graphql.service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import i.learn.spring.boot.graphql.model.Ship;
import i.learn.spring.boot.graphql.repository.ShipRepository;
import i.learn.spring.boot.graphql.service.datafetcher.AllShipsDataFetcher;
import i.learn.spring.boot.graphql.service.datafetcher.ShipDataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class GraphQLService {
    @Value("classpath:ships.graphql")
    Resource resource;

    private GraphQL graphQl;
    @Autowired
    private AllShipsDataFetcher allShipsDataFetcher;
    @Autowired
    private ShipDataFetcher shipDataFetcher;
    @Autowired
    private ShipRepository shipRepository;

    @PostConstruct
    private void loadSchema() throws IOException {
        loadDataIntoHSQL();
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);
        graphQl = GraphQL.newGraphQL(schema).build();
    }

    private void loadDataIntoHSQL() {
        Stream.of(
                        new Ship(1234,"BattleShip",2000.50f,"McCoy",new String[]{"Pierre","Frantz","Dorel"},1993),
                        new Ship(4321,"Cruiser",1224f,"Jesse",new String[]{"Gigi","Mark"},2005),
                        new Ship(1111,"Destroyer",25033.43f,"MuffinMan",new String[]{"CookieMonster","Jean","Mitica","Arthur"},1943)
                )
                .forEach(ship -> shipRepository.save(ship));
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allShips", allShipsDataFetcher)
                        .dataFetcher("ship", shipDataFetcher))
                .build();
    }

    public GraphQL getGraphQl() {
        return graphQl;
    }
}
