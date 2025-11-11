# ReportAPIApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getPatientByDay**](ReportAPIApi.md#getpatientbyday) | **GET** /api/admin/report/patient-by-day |  |
| [**getPatientByHour**](ReportAPIApi.md#getpatientbyhour) | **GET** /api/admin/report/patient-by-hour |  |
| [**getQueueStatusToday**](ReportAPIApi.md#getqueuestatustoday) | **GET** /api/admin/report/queue-status-today |  |
| [**getRevenueDaily**](ReportAPIApi.md#getrevenuedaily) | **GET** /api/admin/report/revenue-daily |  |
| [**getServiceUsage**](ReportAPIApi.md#getserviceusage) | **GET** /api/admin/report/service-usage |  |
| [**getSpecialtyDistribution**](ReportAPIApi.md#getspecialtydistribution) | **GET** /api/admin/report/patient-specialty |  |
| [**getStaffWorkload**](ReportAPIApi.md#getstaffworkload) | **GET** /api/admin/report/staff-workload |  |
| [**getTodaySummary**](ReportAPIApi.md#gettodaysummary) | **GET** /api/admin/report/today |  |



## getPatientByDay

> CustomApiResponseListPatientByDayResponse getPatientByDay(from, to)



### Example

```ts
import {
  Configuration,
  ReportAPIApi,
} from '';
import type { GetPatientByDayRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ReportAPIApi(config);

  const body = {
    // Date
    from: 2013-10-20,
    // Date
    to: 2013-10-20,
  } satisfies GetPatientByDayRequest;

  try {
    const data = await api.getPatientByDay(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **from** | `Date` |  | [Defaults to `undefined`] |
| **to** | `Date` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseListPatientByDayResponse**](CustomApiResponseListPatientByDayResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getPatientByHour

> CustomApiResponseListPatientByHourResponse getPatientByHour()



### Example

```ts
import {
  Configuration,
  ReportAPIApi,
} from '';
import type { GetPatientByHourRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ReportAPIApi(config);

  try {
    const data = await api.getPatientByHour();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**CustomApiResponseListPatientByHourResponse**](CustomApiResponseListPatientByHourResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getQueueStatusToday

> CustomApiResponseMapStringLong getQueueStatusToday()



### Example

```ts
import {
  Configuration,
  ReportAPIApi,
} from '';
import type { GetQueueStatusTodayRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ReportAPIApi(config);

  try {
    const data = await api.getQueueStatusToday();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**CustomApiResponseMapStringLong**](CustomApiResponseMapStringLong.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getRevenueDaily

> CustomApiResponseListRevenueDailyResponse getRevenueDaily(from, to)



### Example

```ts
import {
  Configuration,
  ReportAPIApi,
} from '';
import type { GetRevenueDailyRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ReportAPIApi(config);

  const body = {
    // Date
    from: 2013-10-20,
    // Date
    to: 2013-10-20,
  } satisfies GetRevenueDailyRequest;

  try {
    const data = await api.getRevenueDaily(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **from** | `Date` |  | [Defaults to `undefined`] |
| **to** | `Date` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseListRevenueDailyResponse**](CustomApiResponseListRevenueDailyResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getServiceUsage

> CustomApiResponseListServiceUsageResponse getServiceUsage(from, to)



### Example

```ts
import {
  Configuration,
  ReportAPIApi,
} from '';
import type { GetServiceUsageRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ReportAPIApi(config);

  const body = {
    // Date
    from: 2013-10-20,
    // Date
    to: 2013-10-20,
  } satisfies GetServiceUsageRequest;

  try {
    const data = await api.getServiceUsage(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **from** | `Date` |  | [Defaults to `undefined`] |
| **to** | `Date` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseListServiceUsageResponse**](CustomApiResponseListServiceUsageResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getSpecialtyDistribution

> CustomApiResponseListSpecialtyDistributionResponse getSpecialtyDistribution()



### Example

```ts
import {
  Configuration,
  ReportAPIApi,
} from '';
import type { GetSpecialtyDistributionRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ReportAPIApi(config);

  try {
    const data = await api.getSpecialtyDistribution();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**CustomApiResponseListSpecialtyDistributionResponse**](CustomApiResponseListSpecialtyDistributionResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getStaffWorkload

> CustomApiResponseListStaffWorkloadResponse getStaffWorkload(from, to)



### Example

```ts
import {
  Configuration,
  ReportAPIApi,
} from '';
import type { GetStaffWorkloadRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ReportAPIApi(config);

  const body = {
    // Date
    from: 2013-10-20,
    // Date
    to: 2013-10-20,
  } satisfies GetStaffWorkloadRequest;

  try {
    const data = await api.getStaffWorkload(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **from** | `Date` |  | [Defaults to `undefined`] |
| **to** | `Date` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseListStaffWorkloadResponse**](CustomApiResponseListStaffWorkloadResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getTodaySummary

> CustomApiResponseTodaySummaryResponse getTodaySummary()



### Example

```ts
import {
  Configuration,
  ReportAPIApi,
} from '';
import type { GetTodaySummaryRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ReportAPIApi(config);

  try {
    const data = await api.getTodaySummary();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**CustomApiResponseTodaySummaryResponse**](CustomApiResponseTodaySummaryResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)

