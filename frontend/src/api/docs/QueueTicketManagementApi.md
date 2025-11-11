# QueueTicketManagementApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**callQueueTicket**](#callqueueticket) | **POST** /api/queue-tickets/{queueTicketId}/call | |
|[**doneQueueTicket**](#donequeueticket) | **POST** /api/queue-tickets/{queueTicketId}/done | |
|[**getQueueForDoctorToday**](#getqueuefordoctortoday) | **GET** /api/queue-tickets/doctor/today | |
|[**requeueQueueTicket**](#requeuequeueticket) | **POST** /api/queue-tickets/{queueTicketId}/requeue | |
|[**resumeQueueTicket**](#resumequeueticket) | **POST** /api/queue-tickets/{queueTicketId}/resume | |
|[**skipQueueTicket**](#skipqueueticket) | **POST** /api/queue-tickets/{queueTicketId}/skip | |
|[**startQueueTicket**](#startqueueticket) | **POST** /api/queue-tickets/{queueTicketId}/start | |
|[**waitResultQueueTicket**](#waitresultqueueticket) | **POST** /api/queue-tickets/{queueTicketId}/wait-result | |

# **callQueueTicket**
> CustomApiResponseQueueTicketResponse callQueueTicket()


### Example

```typescript
import {
    QueueTicketManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new QueueTicketManagementApi(configuration);

let queueTicketId: string; // (default to undefined)

const { status, data } = await apiInstance.callQueueTicket(
    queueTicketId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **queueTicketId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseQueueTicketResponse**

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

# **doneQueueTicket**
> CustomApiResponseQueueTicketResponse doneQueueTicket()


### Example

```typescript
import {
    QueueTicketManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new QueueTicketManagementApi(configuration);

let queueTicketId: string; // (default to undefined)

const { status, data } = await apiInstance.doneQueueTicket(
    queueTicketId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **queueTicketId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseQueueTicketResponse**

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

# **getQueueForDoctorToday**
> CustomApiResponseListQueueTicketResponse getQueueForDoctorToday()


### Example

```typescript
import {
    QueueTicketManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new QueueTicketManagementApi(configuration);

const { status, data } = await apiInstance.getQueueForDoctorToday();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseListQueueTicketResponse**

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

# **requeueQueueTicket**
> CustomApiResponseQueueTicketResponse requeueQueueTicket()


### Example

```typescript
import {
    QueueTicketManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new QueueTicketManagementApi(configuration);

let queueTicketId: string; // (default to undefined)

const { status, data } = await apiInstance.requeueQueueTicket(
    queueTicketId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **queueTicketId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseQueueTicketResponse**

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

# **resumeQueueTicket**
> CustomApiResponseQueueTicketResponse resumeQueueTicket()


### Example

```typescript
import {
    QueueTicketManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new QueueTicketManagementApi(configuration);

let queueTicketId: string; // (default to undefined)

const { status, data } = await apiInstance.resumeQueueTicket(
    queueTicketId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **queueTicketId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseQueueTicketResponse**

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

# **skipQueueTicket**
> CustomApiResponseQueueTicketResponse skipQueueTicket()


### Example

```typescript
import {
    QueueTicketManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new QueueTicketManagementApi(configuration);

let queueTicketId: string; // (default to undefined)

const { status, data } = await apiInstance.skipQueueTicket(
    queueTicketId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **queueTicketId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseQueueTicketResponse**

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

# **startQueueTicket**
> CustomApiResponseQueueTicketResponse startQueueTicket()


### Example

```typescript
import {
    QueueTicketManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new QueueTicketManagementApi(configuration);

let queueTicketId: string; // (default to undefined)

const { status, data } = await apiInstance.startQueueTicket(
    queueTicketId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **queueTicketId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseQueueTicketResponse**

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

# **waitResultQueueTicket**
> CustomApiResponseQueueTicketResponse waitResultQueueTicket()


### Example

```typescript
import {
    QueueTicketManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new QueueTicketManagementApi(configuration);

let queueTicketId: string; // (default to undefined)

const { status, data } = await apiInstance.waitResultQueueTicket(
    queueTicketId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **queueTicketId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseQueueTicketResponse**

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

