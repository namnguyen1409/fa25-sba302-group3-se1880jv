# StaffManagementApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**create10**](StaffManagementApi.md#create10) | **POST** /api/admin/staffs |  |
| [**createStaffSchedule**](StaffManagementApi.md#createstaffschedule) | **POST** /api/admin/staffs/{staffId}/schedule |  |
| [**createStaffScheduleTemplate**](StaffManagementApi.md#createstaffscheduletemplate) | **POST** /api/admin/staffs/{staffId}/schedule-template |  |
| [**delete10**](StaffManagementApi.md#delete10) | **DELETE** /api/admin/staffs/{id} |  |
| [**deleteStaffSchedule**](StaffManagementApi.md#deletestaffschedule) | **DELETE** /api/admin/staffs/schedule/{scheduleId} |  |
| [**deleteStaffScheduleTemplate**](StaffManagementApi.md#deletestaffscheduletemplate) | **DELETE** /api/admin/staffs/schedule-template/{templateId} |  |
| [**filter12**](StaffManagementApi.md#filter12) | **POST** /api/admin/staffs/filter |  |
| [**getById6**](StaffManagementApi.md#getbyid6) | **GET** /api/admin/staffs/{id} |  |
| [**getStaffSchedule**](StaffManagementApi.md#getstaffschedule) | **POST** /api/admin/staffs/{staffId}/schedule/filter |  |
| [**getStaffScheduleTemplate**](StaffManagementApi.md#getstaffscheduletemplate) | **POST** /api/admin/staffs/{staffId}/schedule-template/filter |  |
| [**update10**](StaffManagementApi.md#update10) | **PUT** /api/admin/staffs/{id} |  |
| [**updateStaffSchedule**](StaffManagementApi.md#updatestaffschedule) | **PUT** /api/admin/staffs/schedule/{scheduleId} |  |
| [**updateStaffScheduleTemplate**](StaffManagementApi.md#updatestaffscheduletemplate) | **PUT** /api/admin/staffs/schedule-template/{templateId} |  |



## create10

> CustomApiResponseStaffResponse create10(staffRequest)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { Create10Request } from '';

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
  } satisfies Create10Request;

  try {
    const data = await api.create10(body);
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


## delete10

> CustomApiResponseVoid delete10(id)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { Delete10Request } from '';

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
  } satisfies Delete10Request;

  try {
    const data = await api.delete10(body);
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


## filter12

> CustomApiResponsePageStaffResponse filter12(searchFilter)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { Filter12Request } from '';

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
  } satisfies Filter12Request;

  try {
    const data = await api.filter12(body);
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


## getById6

> CustomApiResponseStaffResponse getById6(id)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { GetById6Request } from '';

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
  } satisfies GetById6Request;

  try {
    const data = await api.getById6(body);
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


## update10

> CustomApiResponseStaffResponse update10(id, staffRequest)



### Example

```ts
import {
  Configuration,
  StaffManagementApi,
} from '';
import type { Update10Request } from '';

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
  } satisfies Update10Request;

  try {
    const data = await api.update10(body);
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

