# StaffManagementApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**create11**](StaffManagementApi.md#create11) | **POST** /api/admin/staffs |  |
| [**createStaffSchedule**](StaffManagementApi.md#createstaffschedule) | **POST** /api/admin/staffs/{staffId}/schedule |  |
| [**createStaffScheduleTemplate**](StaffManagementApi.md#createstaffscheduletemplate) | **POST** /api/admin/staffs/{staffId}/schedule-template |  |
| [**dayOff**](StaffManagementApi.md#dayoff) | **POST** /api/admin/staffs/schedule/day-off |  |
| [**delete11**](StaffManagementApi.md#delete11) | **DELETE** /api/admin/staffs/{id} |  |
| [**deleteStaffSchedule**](StaffManagementApi.md#deletestaffschedule) | **DELETE** /api/admin/staffs/schedule/{scheduleId} |  |
| [**deleteStaffScheduleTemplate**](StaffManagementApi.md#deletestaffscheduletemplate) | **DELETE** /api/admin/staffs/schedule-template/{templateId} |  |
| [**filter13**](StaffManagementApi.md#filter13) | **POST** /api/admin/staffs/filter |  |
| [**generate**](StaffManagementApi.md#generate) | **POST** /api/admin/staffs/{staffId}/schedule/generate |  |
| [**getById7**](StaffManagementApi.md#getbyid7) | **GET** /api/admin/staffs/{id} |  |
| [**getMySchedule**](StaffManagementApi.md#getmyschedule) | **POST** /api/admin/staffs/schedule/filter |  |
| [**getStaffSchedule**](StaffManagementApi.md#getstaffschedule) | **POST** /api/admin/staffs/{staffId}/schedule/filter |  |
| [**getStaffScheduleTemplate**](StaffManagementApi.md#getstaffscheduletemplate) | **POST** /api/admin/staffs/{staffId}/schedule-template/filter |  |
| [**markStatus**](StaffManagementApi.md#markstatus) | **PATCH** /api/admin/staffs/schedule/{scheduleId}/status |  |
| [**myRange**](StaffManagementApi.md#myrange) | **GET** /api/admin/staffs/schedule |  |
| [**range**](StaffManagementApi.md#range) | **GET** /api/admin/staffs/{staffId}/schedule |  |
| [**update11**](StaffManagementApi.md#update11) | **PUT** /api/admin/staffs/{id} |  |
| [**updateStaffSchedule**](StaffManagementApi.md#updatestaffschedule) | **PUT** /api/admin/staffs/schedule/{scheduleId} |  |
| [**updateStaffScheduleTemplate**](StaffManagementApi.md#updatestaffscheduletemplate) | **PUT** /api/admin/staffs/schedule-template/{templateId} |  |



## create11

> CustomApiResponseStaffResponse create11(staffRequest)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { Create11Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // StaffRequest
    staffRequest: ...,
  } satisfies Create11Request;

  try {
    const data = await api.create11(body);
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
| **staffRequest** | [StaffRequest](StaffRequest.md) |  | |

### Return type

[**CustomApiResponseStaffResponse**](CustomApiResponseStaffResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## createStaffSchedule

> CustomApiResponseStaffScheduleResponse createStaffSchedule(staffId, staffScheduleRequest)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { CreateStaffScheduleRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // string
    staffId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // StaffScheduleRequest
    staffScheduleRequest: ...,
  } satisfies CreateStaffScheduleRequest;

  try {
    const data = await api.createStaffSchedule(body);
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
| **staffId** | `string` |  | [Defaults to `undefined`] |
| **staffScheduleRequest** | [StaffScheduleRequest](StaffScheduleRequest.md) |  | |

### Return type

[**CustomApiResponseStaffScheduleResponse**](CustomApiResponseStaffScheduleResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## createStaffScheduleTemplate

> CustomApiResponseStaffScheduleTemplateResponse createStaffScheduleTemplate(staffId, staffScheduleTemplateRequest)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { CreateStaffScheduleTemplateRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // string
    staffId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // StaffScheduleTemplateRequest
    staffScheduleTemplateRequest: ...,
  } satisfies CreateStaffScheduleTemplateRequest;

  try {
    const data = await api.createStaffScheduleTemplate(body);
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
| **staffId** | `string` |  | [Defaults to `undefined`] |
| **staffScheduleTemplateRequest** | [StaffScheduleTemplateRequest](StaffScheduleTemplateRequest.md) |  | |

### Return type

[**CustomApiResponseStaffScheduleTemplateResponse**](CustomApiResponseStaffScheduleTemplateResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## dayOff

> CustomApiResponseStaffScheduleResponse dayOff(staffScheduleDayOffRequest)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { DayOffRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // StaffScheduleDayOffRequest
    staffScheduleDayOffRequest: ...,
  } satisfies DayOffRequest;

  try {
    const data = await api.dayOff(body);
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
| **staffScheduleDayOffRequest** | [StaffScheduleDayOffRequest](StaffScheduleDayOffRequest.md) |  | |

### Return type

[**CustomApiResponseStaffScheduleResponse**](CustomApiResponseStaffScheduleResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## delete11

> CustomApiResponseVoid delete11(id)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { Delete11Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies Delete11Request;

  try {
    const data = await api.delete11(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

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


## deleteStaffSchedule

> CustomApiResponseVoid deleteStaffSchedule(scheduleId)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { DeleteStaffScheduleRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // string
    scheduleId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies DeleteStaffScheduleRequest;

  try {
    const data = await api.deleteStaffSchedule(body);
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
| **scheduleId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

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


## deleteStaffScheduleTemplate

> CustomApiResponseVoid deleteStaffScheduleTemplate(templateId)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { DeleteStaffScheduleTemplateRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // string
    templateId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies DeleteStaffScheduleTemplateRequest;

  try {
    const data = await api.deleteStaffScheduleTemplate(body);
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
| **templateId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

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


## filter13

> CustomApiResponsePageStaffResponse filter13(searchFilter)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { Filter13Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies Filter13Request;

  try {
    const data = await api.filter13(body);
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
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePageStaffResponse**](CustomApiResponsePageStaffResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## generate

> CustomApiResponseVoid generate(staffId, staffScheduleGenerateRequest)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { GenerateRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // string
    staffId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // StaffScheduleGenerateRequest (optional)
    staffScheduleGenerateRequest: ...,
  } satisfies GenerateRequest;

  try {
    const data = await api.generate(body);
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
| **staffId** | `string` |  | [Defaults to `undefined`] |
| **staffScheduleGenerateRequest** | [StaffScheduleGenerateRequest](StaffScheduleGenerateRequest.md) |  | [Optional] |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getById7

> CustomApiResponseStaffResponse getById7(id)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { GetById7Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies GetById7Request;

  try {
    const data = await api.getById7(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseStaffResponse**](CustomApiResponseStaffResponse.md)

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


## getMySchedule

> CustomApiResponsePageStaffScheduleResponse getMySchedule(searchFilter)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { GetMyScheduleRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies GetMyScheduleRequest;

  try {
    const data = await api.getMySchedule(body);
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
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePageStaffScheduleResponse**](CustomApiResponsePageStaffScheduleResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getStaffSchedule

> CustomApiResponsePageStaffScheduleResponse getStaffSchedule(staffId, searchFilter)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { GetStaffScheduleRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // string
    staffId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // SearchFilter
    searchFilter: ...,
  } satisfies GetStaffScheduleRequest;

  try {
    const data = await api.getStaffSchedule(body);
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
| **staffId** | `string` |  | [Defaults to `undefined`] |
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePageStaffScheduleResponse**](CustomApiResponsePageStaffScheduleResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getStaffScheduleTemplate

> CustomApiResponsePageStaffScheduleTemplateResponse getStaffScheduleTemplate(staffId, searchFilter)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { GetStaffScheduleTemplateRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // string
    staffId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // SearchFilter
    searchFilter: ...,
  } satisfies GetStaffScheduleTemplateRequest;

  try {
    const data = await api.getStaffScheduleTemplate(body);
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
| **staffId** | `string` |  | [Defaults to `undefined`] |
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePageStaffScheduleTemplateResponse**](CustomApiResponsePageStaffScheduleTemplateResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## markStatus

> CustomApiResponseStaffScheduleResponse markStatus(scheduleId, status, note)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { MarkStatusRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // string
    scheduleId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // 'AVAILABLE' | 'OFF' | 'CANCELLED' | 'CHANGED'
    status: status_example,
    // string (optional)
    note: note_example,
  } satisfies MarkStatusRequest;

  try {
    const data = await api.markStatus(body);
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
| **scheduleId** | `string` |  | [Defaults to `undefined`] |
| **status** | `AVAILABLE`, `OFF`, `CANCELLED`, `CHANGED` |  | [Defaults to `undefined`] [Enum: AVAILABLE, OFF, CANCELLED, CHANGED] |
| **note** | `string` |  | [Optional] [Defaults to `undefined`] |

### Return type

[**CustomApiResponseStaffScheduleResponse**](CustomApiResponseStaffScheduleResponse.md)

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


## myRange

> CustomApiResponseListStaffScheduleResponse myRange(from, to)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { MyRangeRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // Date
    from: 2013-10-20,
    // Date
    to: 2013-10-20,
  } satisfies MyRangeRequest;

  try {
    const data = await api.myRange(body);
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

[**CustomApiResponseListStaffScheduleResponse**](CustomApiResponseListStaffScheduleResponse.md)

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


## range

> CustomApiResponseListStaffScheduleResponse range(staffId, from, to)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { RangeRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // string
    staffId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // Date
    from: 2013-10-20,
    // Date
    to: 2013-10-20,
  } satisfies RangeRequest;

  try {
    const data = await api.range(body);
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
| **staffId** | `string` |  | [Defaults to `undefined`] |
| **from** | `Date` |  | [Defaults to `undefined`] |
| **to** | `Date` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseListStaffScheduleResponse**](CustomApiResponseListStaffScheduleResponse.md)

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


## update11

> CustomApiResponseStaffResponse update11(id, staffRequest)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { Update11Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // StaffRequest
    staffRequest: ...,
  } satisfies Update11Request;

  try {
    const data = await api.update11(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |
| **staffRequest** | [StaffRequest](StaffRequest.md) |  | |

### Return type

[**CustomApiResponseStaffResponse**](CustomApiResponseStaffResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## updateStaffSchedule

> CustomApiResponseStaffScheduleResponse updateStaffSchedule(scheduleId, staffScheduleRequest)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { UpdateStaffScheduleRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // string
    scheduleId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // StaffScheduleRequest
    staffScheduleRequest: ...,
  } satisfies UpdateStaffScheduleRequest;

  try {
    const data = await api.updateStaffSchedule(body);
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
| **scheduleId** | `string` |  | [Defaults to `undefined`] |
| **staffScheduleRequest** | [StaffScheduleRequest](StaffScheduleRequest.md) |  | |

### Return type

[**CustomApiResponseStaffScheduleResponse**](CustomApiResponseStaffScheduleResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## updateStaffScheduleTemplate

> CustomApiResponseStaffScheduleTemplateResponse updateStaffScheduleTemplate(templateId, staffScheduleTemplateRequest)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { UpdateStaffScheduleTemplateRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new StaffManagementApi(config);

  const body = {
    // string
    templateId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // StaffScheduleTemplateRequest
    staffScheduleTemplateRequest: ...,
  } satisfies UpdateStaffScheduleTemplateRequest;

  try {
    const data = await api.updateStaffScheduleTemplate(body);
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
| **templateId** | `string` |  | [Defaults to `undefined`] |
| **staffScheduleTemplateRequest** | [StaffScheduleTemplateRequest](StaffScheduleTemplateRequest.md) |  | |

### Return type

[**CustomApiResponseStaffScheduleTemplateResponse**](CustomApiResponseStaffScheduleTemplateResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)

