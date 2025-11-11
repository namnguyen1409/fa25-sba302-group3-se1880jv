# ReportAPIApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**getPatientByDay**](#getpatientbyday) | **GET** /api/admin/report/patient-by-day | |
|[**getPatientByHour**](#getpatientbyhour) | **GET** /api/admin/report/patient-by-hour | |
|[**getQueueStatusToday**](#getqueuestatustoday) | **GET** /api/admin/report/queue-status-today | |
|[**getRevenueDaily**](#getrevenuedaily) | **GET** /api/admin/report/revenue-daily | |
|[**getServiceUsage**](#getserviceusage) | **GET** /api/admin/report/service-usage | |
|[**getSpecialtyDistribution**](#getspecialtydistribution) | **GET** /api/admin/report/patient-specialty | |
|[**getStaffWorkload**](#getstaffworkload) | **GET** /api/admin/report/staff-workload | |
|[**getTodaySummary**](#gettodaysummary) | **GET** /api/admin/report/today | |

# **getPatientByDay**
> CustomApiResponseListPatientByDayResponse getPatientByDay()


### Example

```typescript
import {
    ReportAPIApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ReportAPIApi(configuration);

let from: string; // (default to undefined)
let to: string; // (default to undefined)

const { status, data } = await apiInstance.getPatientByDay(
    from,
    to
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **from** | [**string**] |  | defaults to undefined|
| **to** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseListPatientByDayResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getPatientByHour**
> CustomApiResponseListPatientByHourResponse getPatientByHour()


### Example

```typescript
import {
    ReportAPIApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ReportAPIApi(configuration);

const { status, data } = await apiInstance.getPatientByHour();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseListPatientByHourResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getQueueStatusToday**
> CustomApiResponseMapStringLong getQueueStatusToday()


### Example

```typescript
import {
    ReportAPIApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ReportAPIApi(configuration);

const { status, data } = await apiInstance.getQueueStatusToday();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseMapStringLong**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getRevenueDaily**
> CustomApiResponseListRevenueDailyResponse getRevenueDaily()


### Example

```typescript
import {
    ReportAPIApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ReportAPIApi(configuration);

let from: string; // (default to undefined)
let to: string; // (default to undefined)

const { status, data } = await apiInstance.getRevenueDaily(
    from,
    to
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **from** | [**string**] |  | defaults to undefined|
| **to** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseListRevenueDailyResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getServiceUsage**
> CustomApiResponseListServiceUsageResponse getServiceUsage()


### Example

```typescript
import {
    ReportAPIApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ReportAPIApi(configuration);

let from: string; // (default to undefined)
let to: string; // (default to undefined)

const { status, data } = await apiInstance.getServiceUsage(
    from,
    to
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **from** | [**string**] |  | defaults to undefined|
| **to** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseListServiceUsageResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getSpecialtyDistribution**
> CustomApiResponseListSpecialtyDistributionResponse getSpecialtyDistribution()


### Example

```typescript
import {
    ReportAPIApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ReportAPIApi(configuration);

const { status, data } = await apiInstance.getSpecialtyDistribution();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseListSpecialtyDistributionResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getStaffWorkload**
> CustomApiResponseListStaffWorkloadResponse getStaffWorkload()


### Example

```typescript
import {
    ReportAPIApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ReportAPIApi(configuration);

let from: string; // (default to undefined)
let to: string; // (default to undefined)

const { status, data } = await apiInstance.getStaffWorkload(
    from,
    to
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **from** | [**string**] |  | defaults to undefined|
| **to** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseListStaffWorkloadResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getTodaySummary**
> CustomApiResponseTodaySummaryResponse getTodaySummary()


### Example

```typescript
import {
    ReportAPIApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ReportAPIApi(configuration);

const { status, data } = await apiInstance.getTodaySummary();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseTodaySummaryResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

