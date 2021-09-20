package i.learn.spring.boot.graphql.service.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import i.learn.spring.boot.graphql.model.Ship;
import i.learn.spring.boot.graphql.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllShipsDataFetcher implements DataFetcher<List<Ship>> {

    @Autowired
    private ShipRepository shipRepository;

    @Override
    public List<Ship> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return shipRepository.findAll();
    }
}
