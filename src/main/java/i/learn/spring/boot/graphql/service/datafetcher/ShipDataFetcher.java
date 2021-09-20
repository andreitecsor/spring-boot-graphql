package i.learn.spring.boot.graphql.service.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import i.learn.spring.boot.graphql.model.Ship;
import i.learn.spring.boot.graphql.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShipDataFetcher implements DataFetcher<Ship> {

    @Autowired
    private ShipRepository shipRepository;

    @Override
    public Ship get(DataFetchingEnvironment dataFetchingEnvironment) {
        Integer serialNo = dataFetchingEnvironment.getArgument("id");
        return shipRepository.findById(serialNo).orElse(null);
    }
}
