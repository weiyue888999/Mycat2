package io.mycat;

import io.mycat.calcite.CodeExecuterContext;
import io.mycat.calcite.logical.MycatView;
import io.mycat.calcite.physical.MycatMergeSort;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.function.Function1;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.util.RxBuiltInMethodImpl;

import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AsyncMycatDataContextImpl extends NewMycatDataContextImpl {
    private final IdentityHashMap<RelNode, List<Observable<Object[]>>> viewMap;

    public AsyncMycatDataContextImpl(MycatDataContext dataContext,
                                     CodeExecuterContext context,
                                     IdentityHashMap<RelNode, List<Observable<Object[]>>> map,
                                     List<Object> params,
                                     boolean forUpdate) {
        super(dataContext, context, params, forUpdate);
        this.viewMap = map;
    }


    @Override
    public Enumerable<Object[]> getEnumerable(RelNode node) {
        return Linq4j.asEnumerable(getObservable(node).blockingIterable());
    }

    @Override
    public Enumerable<Object[]> getEnumerable(RelNode node, Function1 function1, Comparator comparator, int offset, int fetch) {
        return Linq4j.asEnumerable(getObservable(node, function1, comparator, offset, fetch).blockingIterable());
    }

    @Override
    public Observable<Object[]> getObservable(RelNode node) {
        List<Observable<Object[]>> observables = viewMap.get(node);
        return Observable.merge(observables);
    }

    @Override
    public Observable<Object[]> getObservable(RelNode relNode, Function1 function1, Comparator comparator, int offset, int fetch) {
            return MycatMergeSort.streamOrderBy(viewMap.get(relNode), function1, comparator, offset, fetch);

    }

}
