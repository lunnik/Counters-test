package com.example.counters.data.data_source.dummy

import com.example.cache.domain.entity.Counter
import com.example.counters.data.CountersDataSource
import com.example.counters.domain.use_case.add_counter.AddCounterFailure
import com.example.counters.domain.use_case.add_counter.AddCounterResponse
import com.example.counters.domain.use_case.decrease_counter.DecreaseCounterFailure
import com.example.counters.domain.use_case.decrease_counter.DecreaseCounterResponse
import com.example.counters.domain.use_case.delete_counter.DeleteCounterFailure
import com.example.counters.domain.use_case.delete_counter.DeleteCounterResponse
import com.example.counters.domain.use_case.get_counters.GetCountersFailure
import com.example.counters.domain.use_case.get_counters.GetCountersResponse
import com.example.counters.domain.use_case.increase_counter.IncreaseCounterFailure
import com.example.counters.domain.use_case.increase_counter.IncreaseCounterResponse
import com.example.domain.Either

class CountersDataSourceDummy : CountersDataSource {
    /** */
    override suspend fun getCounters(): Either<GetCountersFailure, GetCountersResponse> {
        return Either.Right(GetCountersResponse(getResponseCommon()))
    }

    /** */
    override suspend fun increaseCounters(id: String): Either<IncreaseCounterFailure, IncreaseCounterResponse> {
        return Either.Right(IncreaseCounterResponse(getResponseCommon()))
    }

    /** */
    override suspend fun decreaseCounters(id: String): Either<DecreaseCounterFailure, DecreaseCounterResponse> {
        return Either.Right(DecreaseCounterResponse(getResponseCommon()))
    }

    /** */
    override suspend fun deleteCounters(id: String): Either<DeleteCounterFailure, DeleteCounterResponse> {
        return Either.Right(DeleteCounterResponse(getResponseCommon()))
    }

    /** */
    override suspend fun addCounters(title: String): Either<AddCounterFailure, AddCounterResponse> {
        return Either.Right(AddCounterResponse(getResponseCommon()))
    }

    private fun getResponseCommon(): List<Counter> {
        val counter1 = Counter("12325", 0, "Cook")
        val counter2 = Counter("12435", 1, "cake")
        val counter3 = Counter("jd74e", 3, "eggs")
        val counter4 = Counter("393je", 5, "hot-dog")
        val counter5 = Counter("393je", 4, "cereal")
        return arrayListOf(counter1, counter2, counter3, counter4, counter5)
    }
}